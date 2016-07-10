package com.goo32v2.cooldict.data.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.goo32v2.cooldict.data.WordDataSource;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.data.sources.database.DatabaseHelper;
import com.goo32v2.cooldict.data.sources.database.DatabasePersistenceContract.DictionaryEntry;
import com.goo32v2.cooldict.data.sources.database.DatabasePersistenceContract.WordsEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 15-May-16. (c) CoolDict
 */
public class WordDao implements WordDataSource {

    // TODO: 07.07.16 update app, remove old code
    // TODO: 07.07.16 dao and repository must implement the same interface
    // TODO: 07.07.16 update cache source

    private static WordDao INSTANCE;
    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase db;

    private WordDao(@NonNull Context context){
        mDatabaseHelper = new DatabaseHelper(context);
    }

    public static WordDao getInstance(@NonNull Context context){
        if (INSTANCE == null){
            INSTANCE = new WordDao(context);
        }
        return INSTANCE;
    }

    public void get(@NonNull GetListCallback<WordModel> callback,
                    @Nullable String selection,
                    @Nullable String[] selectionArgs,
                    @Nullable String orderBy) {
        db = mDatabaseHelper.getReadableDatabase();

        String query = getQuery(selection, orderBy);

        Log.d(this.getClass().getCanonicalName(), query);

        Cursor c = db.rawQuery(query, selectionArgs);
        List<WordModel> words = populate(c);
        c.close();
        db.close();

        if (words.isEmpty()){
            callback.onDataNotAvailable();
        } else {
            callback.onLoaded(words);
        }
    }

    @NonNull
    private String getQuery(@Nullable String selection, @Nullable String orderBy){
        StringBuilder query = new StringBuilder();
        query.append("SELECT w.").append(WordsEntry.COLUMN_ENTRY_ID).append(", ")
                .append("w.").append(WordsEntry.COLUMN_ORIGINAL_WORD).append(", ")
                .append("w.").append(WordsEntry.COLUMN_TRANSLATED_WORD).append(", ")
                .append("d.").append(DictionaryEntry.COLUMN_ENTRY_ID).append(", ")
                .append("d.").append(DictionaryEntry.COLUMN_TITLE)
                .append(" FROM ").append(WordsEntry.TABLE_NAME).append(" AS w JOIN ")
                .append(DictionaryEntry.TABLE_NAME).append(" AS d ON ")
                .append("d.").append(DictionaryEntry.COLUMN_ENTRY_ID).append("=w.")
                .append(WordsEntry.COLUMN_DICTIONARY_ID);

        if(selection != null && !selection.isEmpty()){
            query.append(" WHERE ").append(selection);
        }
        if (orderBy != null && !orderBy.isEmpty()){
            query.append(" ORDER BY ").append(orderBy);
        }

        return query.toString();
    }

    private List<WordModel> populate(@NonNull Cursor c){
        List<WordModel> words = new ArrayList<>();
        if (c.getCount() > 0 && c.moveToFirst()){
            do {
                String itemId = c.getString(c.getColumnIndexOrThrow(WordsEntry.COLUMN_ENTRY_ID));
                String origWord = c.getString(c.getColumnIndexOrThrow(WordsEntry.COLUMN_ORIGINAL_WORD));
                String translWord = c.getString(c.getColumnIndexOrThrow(WordsEntry.COLUMN_TRANSLATED_WORD));
                String dictId = c.getString(c.getColumnIndexOrThrow(DictionaryEntry.COLUMN_ENTRY_ID));
                String dictTitle = c.getString(c.getColumnIndexOrThrow(DictionaryEntry.COLUMN_TITLE));

                WordModel word = new WordModel(itemId, origWord, translWord, dictId, dictTitle);
                words.add(word);
            } while (c.moveToNext());
        }
        return words;
    }

    @Override
    public void save(@NonNull WordModel word) {
        db = mDatabaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(WordsEntry.COLUMN_ENTRY_ID, word.getId());
        values.put(WordsEntry.COLUMN_ORIGINAL_WORD, word.getOriginalWord());
        values.put(WordsEntry.COLUMN_TRANSLATED_WORD, word.getTranslatedWord());
        values.put(WordsEntry.COLUMN_DICTIONARY_ID, word.getDictionaryId());

        db.insert(WordsEntry.TABLE_NAME, null, values);

        db.close();
    }

    @Override
    public void remove(@NonNull WordModel word) {
        db = mDatabaseHelper.getWritableDatabase();

        String selection = WordsEntry.COLUMN_ENTRY_ID + " LIKE ?";
        String[] selectionArgs = { word.getId() };

        db.delete(WordsEntry.TABLE_NAME, selection, selectionArgs);
        db.close();
    }

    @Override
    public void update(@NonNull WordModel model) {
        db = mDatabaseHelper.getWritableDatabase();

        String whereClause = WordsEntry.COLUMN_ENTRY_ID + "= ?";
        String[] whereArgs = { model.getId() };

        ContentValues values = new ContentValues();
        values.put(WordsEntry.COLUMN_ORIGINAL_WORD, model.getOriginalWord());
        values.put(WordsEntry.COLUMN_TRANSLATED_WORD, model.getTranslatedWord());
        values.put(WordsEntry.COLUMN_DICTIONARY_ID, model.getDictionaryId());

        db.update(WordsEntry.TABLE_NAME, values, whereClause, whereArgs);
        db.close();
    }

    @Override
    public void removeAll() {
        db = mDatabaseHelper.getWritableDatabase();
        db.delete(WordsEntry.TABLE_NAME, null, null);
        db.close();
    }

    @Override
    public void getWordsList(@NonNull GetListCallback<WordModel> callback) {
        get(callback, null, null, null);
    }

    @Override
    public void getWordById(@NonNull String wordId, @NonNull GetListCallback<WordModel> callback) {
        String selection = WordsEntry.COLUMN_ENTRY_ID + " LIKE ?";
        String[] selectionArgs = { wordId };

        get(callback, selection, selectionArgs, null);
    }

    @Override
    public void getWordsByDictionary(@NonNull String dictionaryId, @NonNull GetListCallback<WordModel> callback) {
        String selection = WordsEntry.COLUMN_DICTIONARY_ID + " LIKE ?";
        String[] selectionArgs = { dictionaryId };

        get(callback, selection, selectionArgs, null);
    }
}
