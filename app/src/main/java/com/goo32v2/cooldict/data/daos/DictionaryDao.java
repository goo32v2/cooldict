package com.goo32v2.cooldict.data.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.goo32v2.cooldict.Constants;
import com.goo32v2.cooldict.data.DictDataSource;
import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.sources.database.DatabaseHelper;
import com.goo32v2.cooldict.data.sources.database.DatabasePersistenceContract.DictionaryEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 16-May-16. (c) CoolDict
 */

public class DictionaryDao implements DictDataSource {

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
    public void get(@NonNull GetListCallback<DictionaryModel> callback, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String orderBy) {
        db = mDatabaseHelper.getReadableDatabase();

        String query = getQuery(selection, orderBy);

        Log.d(this.getClass().getCanonicalName(), query);

        Cursor c = db.rawQuery(query, selectionArgs);
        List<DictionaryModel> dictionaries = populate(c);
        c.close();
        db.close();

        if (dictionaries.isEmpty()){
            callback.onDataNotAvailable();
        } else {
            callback.onLoaded(dictionaries);
        }
    }

    @NonNull
    private String getQuery(@Nullable String selection, @Nullable String orderBy){
        StringBuilder query = new StringBuilder();

        query.append("SELECT ").append(DictionaryEntry.COLUMN_ENTRY_ID).append(", ")
                .append(DictionaryEntry.COLUMN_TITLE)
                .append(" FROM ").append(DictionaryEntry.TABLE_NAME);

        if(selection != null && !selection.isEmpty()){
            query.append(" WHERE ").append(selection);
        }
        if (orderBy != null && !orderBy.isEmpty()){
            query.append(" ORDER BY ").append(orderBy);
        }

        return query.toString();
    }

    private List<DictionaryModel> populate(@NonNull Cursor c){
        List<DictionaryModel> dictionaries = new ArrayList<>();
        if (c.getCount() > 0 && c.moveToFirst()){
            do {
                String itemId = c.getString(c.getColumnIndexOrThrow(DictionaryEntry.COLUMN_ENTRY_ID));
                String title = c.getString(c.getColumnIndexOrThrow(DictionaryEntry.COLUMN_TITLE));
                DictionaryModel dictionary = new DictionaryModel(itemId, title);
                dictionaries.add(dictionary);
            } while (c.moveToNext());
        }
        return dictionaries;
    }

    @Override
    public void getDictionaryList(@NonNull GetListCallback<DictionaryModel> callback) {
        get(callback, null, null, null);
    }

    @Override
    public void getDictionaryById(String id, @NonNull GetListCallback<DictionaryModel> callback) {
        String selection = DictionaryEntry.COLUMN_ENTRY_ID + " LIKE ?";
        String[] selectionArgs = { id };

        get(callback, selection, selectionArgs, null);
    }

    @Override
    public void getDictionaryByName(String name, @NonNull GetListCallback<DictionaryModel> callback) {
        String selection = DictionaryEntry.COLUMN_TITLE + " LIKE ?";
        String[] selectionArgs = { name };

        get(callback, selection, selectionArgs, null);
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
