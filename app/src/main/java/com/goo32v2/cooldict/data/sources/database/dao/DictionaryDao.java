package com.goo32v2.cooldict.data.sources.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.sources.SourcesConstants;
import com.goo32v2.cooldict.data.sources.database.DatabaseHelper;
import com.goo32v2.cooldict.data.sources.database.DatabasePersistenceContract.DictionaryEntry;
import com.goo32v2.cooldict.data.sources.interfaces.DictDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created on 16-May-16. (c) CoolDict
 */

public class DictionaryDao implements DictDataSource {

    private static DictionaryDao INSTANCE;
    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase db;

    private DictionaryDao(@NonNull Context context){
        checkNotNull(context);
        mDatabaseHelper = new DatabaseHelper(context);
    }

    public static DictionaryDao getInstance(@NonNull Context context){
        if (INSTANCE == null){
            INSTANCE = new DictionaryDao(context);
        }
        return INSTANCE;
    }

    @Override
    public void get(@NonNull GetListCallback<DictionaryModel> callback) {
        List<DictionaryModel> dictionaries = new ArrayList<>();
        db = mDatabaseHelper.getReadableDatabase();

        String[] projection = {
                DictionaryEntry.COLUMN_ENTRY_ID,
                DictionaryEntry.COLUMN_TITLE,
        };

        Cursor c = db.query(
                DictionaryEntry.TABLE_NAME, projection, null, null, null, null, null
        );

        if (c != null && c.getCount() > 0 && c.moveToFirst()){
                do {
                    String itemId = c.getString(c.getColumnIndexOrThrow(DictionaryEntry.COLUMN_ENTRY_ID));
                    String title = c.getString(c.getColumnIndexOrThrow(DictionaryEntry.COLUMN_TITLE));

                    DictionaryModel dictionary = new DictionaryModel(itemId, title);
                    dictionaries.add(dictionary);
                } while (c.moveToNext());
        }

        if (c != null){
            c.close();
        }
        db.close();

        if (dictionaries.isEmpty()){
            callback.onDataNotAvailable();
        } else {
            callback.onLoaded(dictionaries);
        }
    }

    @Override
    public void get(@NonNull String dictionaryId, @NonNull GetEntryCallback<DictionaryModel> callback) {
        db = mDatabaseHelper.getReadableDatabase();

        String[] projection = {
                DictionaryEntry.COLUMN_ENTRY_ID,
                DictionaryEntry.COLUMN_TITLE,
        };

        String selection = DictionaryEntry.COLUMN_ENTRY_ID + " LIKE ?";
        String[] selectionArgs = { dictionaryId };

        Cursor c = db.query(
                DictionaryEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null
        );

        DictionaryModel dictionary = null;
        if (c != null && c.getCount() > 0 && c.moveToFirst()){
            String itemId = c.getString(c.getColumnIndexOrThrow(DictionaryEntry.COLUMN_ENTRY_ID));
            String title = c.getString(c.getColumnIndexOrThrow(DictionaryEntry.COLUMN_TITLE));
            dictionary = new DictionaryModel(itemId, title);
            }

        if (c != null){
            c.close();
        }
        db.close();

        if (dictionary != null){
            callback.onLoaded(dictionary);
        } else {
            callback.onDataNotAvailable();
        }
    }

    @Override
    public void save(@NonNull DictionaryModel dictionary) {
        checkNotNull(dictionary);
        db = mDatabaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DictionaryEntry.COLUMN_ENTRY_ID, dictionary.getId());
        values.put(DictionaryEntry.COLUMN_TITLE, dictionary.getTitle());

        db.insert(DictionaryEntry.TABLE_NAME, null, values);
        db.close();
    }

    @Override
    public void remove(@NonNull String dictionaryId) {
        db = mDatabaseHelper.getWritableDatabase();

        String selection = DictionaryEntry.COLUMN_ENTRY_ID + " LIKE ?";
        String[] selectionArgs = { dictionaryId };

        db.delete(DictionaryEntry.TABLE_NAME, selection,selectionArgs);

        db.close();
    }

    @Override
    public void remove(@NonNull DictionaryModel dictionary) {
        remove(dictionary.getId());
    }

    @Override
    public void update(String id, DictionaryModel newModel) {
        if (!Objects.equals(newModel.getId(), id)) {
            throw new IllegalArgumentException("ID an newModel.getId() must equals");
        }
        checkNotNull(id);
        checkNotNull(newModel);

        db = mDatabaseHelper.getWritableDatabase();

        String whereClause = DictionaryEntry.COLUMN_ENTRY_ID + "= ?";
        String[] whereArgs = { id };

        ContentValues values = new ContentValues();
        values.put(DictionaryEntry.COLUMN_TITLE, newModel.getTitle());

        db.update(DictionaryEntry.TABLE_NAME, values, whereClause, whereArgs);
        db.close();
    }

    @Override
    public void getDefaultDictionary(@NonNull final GetEntryCallback<DictionaryModel> callback) {
        get(SourcesConstants.DEFAULT_DICTIONARY_ID, new GetEntryCallback<DictionaryModel>() {
            @Override
            public void onLoaded(DictionaryModel entry) {
                callback.onLoaded(entry);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void deleteAll() {
        db = mDatabaseHelper.getWritableDatabase();
        String whereClause = DictionaryEntry.COLUMN_ENTRY_ID + "<> ?";
        String[] whereArgs = { SourcesConstants.DEFAULT_DICTIONARY_ID };
        db.delete(DictionaryEntry.TABLE_NAME, whereClause, whereArgs);
        db.close();
    }
}
