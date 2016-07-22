package com.goo32v2.cooldict.data.daos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.goo32v2.cooldict.data.database.DatabaseHelper;
import com.goo32v2.cooldict.data.database.DatabasePersistence.DictionariesEntry;
import com.goo32v2.cooldict.data.database.DatabasePersistence.WordsEntry;
import com.goo32v2.cooldict.models.DictionaryModel;
import com.goo32v2.cooldict.models.WordModel;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created on 15-May-16. (c) CoolDict
 */
public class WordsDao implements DataSource<WordModel> {

    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase db;

    public WordsDao(@NonNull DatabaseHelper helper){
        mDatabaseHelper = helper;
    }

    @Override
    public Observable<WordModel> get(@Nullable String selection,
                                     @Nullable String[] selectionArgs,
                                     @Nullable String orderBy) {

        String query = getQuery(selection, orderBy);

        db = mDatabaseHelper.getReadableDatabase();
        Cursor c = db.rawQuery(query, selectionArgs);
        List<WordModel> words = populate(c);
        c.close();
        db.close();

        return Observable.from(words);
    }

    @Override
    public void save(@NonNull WordModel word) {
        ContentValues values = new ContentValues();
        values.put(WordsEntry.COLUMN_ENTRY_ID, word.getId());
        values.put(WordsEntry.COLUMN_ORIGINAL_WORD, word.getOriginalWord());
        values.put(WordsEntry.COLUMN_TRANSLATED_WORD, word.getTranslatedWord());
        values.put(WordsEntry.COLUMN_DICTIONARY_ID, word.getDictionaryId());

        db = mDatabaseHelper.getWritableDatabase();
        db.insert(WordsEntry.TABLE_NAME, null, values);
        db.close();
    }

    @Override
    public void remove(@NonNull WordModel word) {
        String selection = WordsEntry.COLUMN_ENTRY_ID + " LIKE ?";
        String[] selectionArgs = { word.getId() };

        db = mDatabaseHelper.getWritableDatabase();
        db.delete(WordsEntry.TABLE_NAME, selection, selectionArgs);
        db.close();
    }

    @Override
    public void remove(@NonNull List<WordModel> entries) {
        List<String> ids = new ArrayList<>();
        for (WordModel entry : entries) {
            ids.add(entry.getId());
        }
        String query = String.format("DELETE FROM " + WordsEntry.TABLE_NAME + " WHERE "
                + WordsEntry.COLUMN_ENTRY_ID + " IN (%s);", TextUtils.join(", ", ids));

        db = mDatabaseHelper.getWritableDatabase();
        db.execSQL(query);
        db.close();
    }

    @Override
    public void update(@NonNull WordModel model) {
        String whereClause = WordsEntry.COLUMN_ENTRY_ID + "= ?";
        String[] whereArgs = { model.getId() };

        ContentValues values = new ContentValues();
        values.put(WordsEntry.COLUMN_ORIGINAL_WORD, model.getOriginalWord());
        values.put(WordsEntry.COLUMN_TRANSLATED_WORD, model.getTranslatedWord());
        values.put(WordsEntry.COLUMN_DICTIONARY_ID, model.getDictionaryId());

        db = mDatabaseHelper.getWritableDatabase();
        db.update(WordsEntry.TABLE_NAME, values, whereClause, whereArgs);
        db.close();
    }

    @NonNull
    private String getQuery(@Nullable String selection, @Nullable String orderBy){
        StringBuilder query = new StringBuilder();
        query.append("SELECT w.").append(WordsEntry.COLUMN_ENTRY_ID).append(", ")
                .append("w.").append(WordsEntry.COLUMN_ORIGINAL_WORD).append(", ")
                .append("w.").append(WordsEntry.COLUMN_TRANSLATED_WORD).append(", ")
                .append("d.").append(DictionariesEntry.COLUMN_ENTRY_ID).append(", ")
                .append("d.").append(DictionariesEntry.COLUMN_TITLE)
                .append(" FROM ").append(WordsEntry.TABLE_NAME).append(" AS w JOIN ")
                .append(DictionariesEntry.TABLE_NAME).append(" AS d ON ")
                .append("d.").append(DictionariesEntry.COLUMN_ENTRY_ID).append("=w.")
                .append(WordsEntry.COLUMN_DICTIONARY_ID);

        if(selection != null && !selection.isEmpty()){
            query.append(" WHERE ").append(selection);
        }
        if (orderBy != null && !orderBy.isEmpty()){
            query.append(" ORDER BY ").append(orderBy);
        }

        return query.toString();
    }

    @NonNull
    private List<WordModel> populate(@NonNull Cursor c){
        List<WordModel> words = new ArrayList<>();
        if (c.getCount() > 0 && c.moveToFirst()){
            do {
                String itemId = c.getString(c.getColumnIndexOrThrow(WordsEntry.COLUMN_ENTRY_ID));
                String origWord = c.getString(c.getColumnIndexOrThrow(WordsEntry.COLUMN_ORIGINAL_WORD));
                String translWord = c.getString(c.getColumnIndexOrThrow(WordsEntry.COLUMN_TRANSLATED_WORD));
                String dictId = c.getString(c.getColumnIndexOrThrow(DictionariesEntry.COLUMN_ENTRY_ID));
                String dictTitle = c.getString(c.getColumnIndexOrThrow(DictionariesEntry.COLUMN_TITLE));

                WordModel word = new WordModel(itemId, origWord, translWord, new DictionaryModel(dictId, dictTitle));
                words.add(word);
            } while (c.moveToNext());
        }
        return words;
    }



}
