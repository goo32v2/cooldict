package com.goo32v2.cooldict.data.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.goo32v2.cooldict.Constants;
import com.goo32v2.cooldict.data.DataSource;
import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.sources.database.DatabaseHelper;
import com.goo32v2.cooldict.data.sources.database.DatabasePersistenceContract.DictionaryEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 16-May-16. (c) CoolDict
 */

public class DictionaryDao implements DataSource<DictionaryModel> {

    private static DictionaryDao INSTANCE;
    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase db;

    private DictionaryDao(@NonNull Context context){
        mDatabaseHelper = new DatabaseHelper(context);
    }

    public static DictionaryDao getInstance(@NonNull Context context){
        if (INSTANCE == null){
            INSTANCE = new DictionaryDao(context);
        }
        return INSTANCE;
    }

    @Override
    public void get(@NonNull GetListCallback<DictionaryModel> callback,
                    String selection,
                    String[] selectionArgs,
                    String orderBy,
                    String groupBy,
                    String having) {

        List<DictionaryModel> dictionaries = new ArrayList<>();
        db = mDatabaseHelper.getReadableDatabase();

        String[] projection = {
                DictionaryEntry.COLUMN_ENTRY_ID,
                DictionaryEntry.COLUMN_TITLE,
        };

        Cursor c = db.query(
                DictionaryEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                groupBy,
                having,
                orderBy
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
    public void save(@NonNull DictionaryModel dictionary) {
        db = mDatabaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DictionaryEntry.COLUMN_ENTRY_ID, dictionary.getId());
        values.put(DictionaryEntry.COLUMN_TITLE, dictionary.getTitle());

        db.insert(DictionaryEntry.TABLE_NAME, null, values);
        db.close();
    }

    @Override
    public void update(@NonNull DictionaryModel model) {
        db = mDatabaseHelper.getWritableDatabase();

        String whereClause = DictionaryEntry.COLUMN_ENTRY_ID + "= ?";
        String[] whereArgs = { model.getId() };

        ContentValues values = new ContentValues();
        values.put(DictionaryEntry.COLUMN_TITLE, model.getTitle());

        db.update(DictionaryEntry.TABLE_NAME, values, whereClause, whereArgs);
        db.close();
    }

    @Override
    public void remove(@NonNull DictionaryModel dictionary) {
        db = mDatabaseHelper.getWritableDatabase();

        String selection = DictionaryEntry.COLUMN_ENTRY_ID + " LIKE ?";
        String[] selectionArgs = { dictionary.getId() };

        db.delete(DictionaryEntry.TABLE_NAME, selection, selectionArgs);

        db.close();
    }

    @Override
    public void removeAll() {
        db = mDatabaseHelper.getWritableDatabase();

        String whereClause = DictionaryEntry.COLUMN_ENTRY_ID + "<> ?";
        String[] whereArgs = { Constants.DEFAULT_DICTIONARY_ID };

        db.delete(DictionaryEntry.TABLE_NAME, whereClause, whereArgs);

        db.close();
    }
}
