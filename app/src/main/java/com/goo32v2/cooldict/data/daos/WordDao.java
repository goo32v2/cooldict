package com.goo32v2.cooldict.data.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.data.sources.database.DatabaseHelper;
import com.goo32v2.cooldict.data.sources.database.DatabasePersistenceContract.WordsEntry;
import com.goo32v2.cooldict.data.DataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created on 15-May-16. (c) CoolDict
 */

// TODO: 16-May-16 implement remote data went done with base app
// TODO: 28-May-16 implement removeAllWordsByDictionary

public class WordDao implements DataSource<WordModel> {

    private static WordDao INSTANCE;
    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase db;

    private WordDao(@NonNull Context context){
        checkNotNull(context);
        mDatabaseHelper = new DatabaseHelper(context);
    }

    public static WordDao getInstance(@NonNull Context context){
        if (INSTANCE == null){
            INSTANCE = new WordDao(context);
        }
        return INSTANCE;
    }


    // TODO: 25-Jun-16 implement other query params
    @Override
    public void get(@NonNull final GetListCallback<WordModel> callback,
                    String selection,
                    String[] selectionArgs) {

        List<WordModel> words = new ArrayList<>();
        db = mDatabaseHelper.getReadableDatabase();

        String[] projection = {
               WordsEntry.COLUMN_ENTRY_ID,
               WordsEntry.COLUMN_ORIGINAL_WORD,
               WordsEntry.COLUMN_TRANSLATED_WORD,
               WordsEntry.COLUMN_DICTIONARY_ID
        };

        Cursor c = db.query(
                WordsEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null
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
        checkNotNull(word);
        db = mDatabaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(WordsEntry.COLUMN_ENTRY_ID, word.getId());
        values.put(WordsEntry.COLUMN_ORIGINAL_WORD, word.getOriginalWord());
        values.put(WordsEntry.COLUMN_TRANSLATED_WORD, word.getTranslatedWord());
        values.put(WordsEntry.COLUMN_DICTIONARY_ID, word.getDictionaryID());

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
    public void update(String id, WordModel newModel) {
        db = mDatabaseHelper.getWritableDatabase();
        if (!Objects.equals(newModel.getId(), id)) {
            throw new IllegalArgumentException("ID an newModel.getId() must equals");
        }
        checkNotNull(id);
        checkNotNull(newModel);

        String whereClause = WordsEntry.COLUMN_ENTRY_ID + "= ?";
        String[] whereArgs = { id };

        ContentValues values = new ContentValues();
        values.put(WordsEntry.COLUMN_ORIGINAL_WORD, newModel.getOriginalWord());
        values.put(WordsEntry.COLUMN_TRANSLATED_WORD, newModel.getTranslatedWord());
        values.put(WordsEntry.COLUMN_DICTIONARY_ID, newModel.getDictionaryID());

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
