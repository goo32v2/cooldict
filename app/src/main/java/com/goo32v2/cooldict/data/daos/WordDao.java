package com.goo32v2.cooldict.data.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.goo32v2.cooldict.data.DataSource;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.data.sources.database.DatabaseHelper;
import com.goo32v2.cooldict.data.sources.database.DatabasePersistenceContract.DictionaryEntry;
import com.goo32v2.cooldict.data.sources.database.DatabasePersistenceContract.WordsEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 15-May-16. (c) CoolDict
 */
public class WordDao implements DataSource<WordModel> {

    // TODO: 07.07.16 make query fabric
    // TODO: 07.07.16 use query with join and new model
    // TODO: 07.07.16 update app, remove old code
    // TODO: 07.07.16 dao and repository must implement the same interface
    // TODO: 07.07.16 update cache source
    // TODO: 07.07.16 create new private method for populating model

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

    public void get(@NonNull GetListCallback<WordModel> callback, @NonNull String query) {
        List<WordModel> words = new ArrayList<>();
        db = mDatabaseHelper.getReadableDatabase();

        Cursor c = db.rawQuery(query, null);

        if (c != null && c.getCount() > 0 && c.moveToFirst()){
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

        if (c != null){
            c.close();
        }
        db.close();

        if (words.isEmpty()){
            callback.onDataNotAvailable();
        } else {
            callback.onLoaded(words);
        }
    }

    @Override
    public void get(@NonNull final GetListCallback<WordModel> callback,
                    String selection,
                    String[] selectionArgs,
                    String orderBy,
                    String groupBy,
                    String having) {

        List<WordModel> words = new ArrayList<>();
        db = mDatabaseHelper.getReadableDatabase();

        String[] projection = {
               WordsEntry.COLUMN_ENTRY_ID,
               WordsEntry.COLUMN_ORIGINAL_WORD,
               WordsEntry.COLUMN_TRANSLATED_WORD,
               WordsEntry.COLUMN_DICTIONARY_ID
        };

        Cursor c = db.query(
                WordsEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                groupBy,
                having,
                orderBy
        );


        if (c != null && c.getCount() > 0 && c.moveToFirst()){
            do {
                String itemId = c.getString(c.getColumnIndexOrThrow(WordsEntry.COLUMN_ENTRY_ID));
                String origWord = c.getString(c.getColumnIndexOrThrow(WordsEntry.COLUMN_ORIGINAL_WORD));
                String translWord = c.getString(c.getColumnIndexOrThrow(WordsEntry.COLUMN_TRANSLATED_WORD));
                String dictId = c.getString(c.getColumnIndexOrThrow(WordsEntry.COLUMN_DICTIONARY_ID));

                WordModel word = new WordModel(itemId, origWord, translWord, dictId);
                words.add(word);
            } while (c.moveToNext());
        }

        if (c != null){
            c.close();
        }
        db.close();

        if (words.isEmpty()){
            callback.onDataNotAvailable();
        } else {
            callback.onLoaded(words);
        }
    }

    @Override
    public void save(@NonNull WordModel word) {
        db = mDatabaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(WordsEntry.COLUMN_ENTRY_ID, word.getId());
        values.put(WordsEntry.COLUMN_ORIGINAL_WORD, word.getOriginalWord());
        values.put(WordsEntry.COLUMN_TRANSLATED_WORD, word.getTranslatedWord());
        values.put(WordsEntry.COLUMN_DICTIONARY_ID, word.getDictionary());

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
        values.put(WordsEntry.COLUMN_DICTIONARY_ID, model.getDictionary());

        db.update(WordsEntry.TABLE_NAME, values, whereClause, whereArgs);
        db.close();
    }

    @Override
    public void removeAll() {
        db = mDatabaseHelper.getWritableDatabase();
        db.delete(WordsEntry.TABLE_NAME, null, null);
        db.close();
    }

}
