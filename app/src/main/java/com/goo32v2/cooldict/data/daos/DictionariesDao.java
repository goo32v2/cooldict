package com.goo32v2.cooldict.data.daos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.goo32v2.cooldict.data.database.DatabaseHelper;
import com.goo32v2.cooldict.data.database.DatabasePersistenceContract.DictionariesEntry;
import com.goo32v2.cooldict.models.DictionaryModel;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created on 16-May-16. (c) CoolDict
 */

public class DictionariesDao implements DataSource<DictionaryModel> {

    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase db;

    public DictionariesDao(@NonNull DatabaseHelper dbHelper) {
        mDatabaseHelper = dbHelper;
    }

    @Override
    public Observable<DictionaryModel> get(@Nullable String selection,
                                           @Nullable String[] selectionArgs,
                                           @Nullable String orderBy) {
        String query = createSelectionQuery(selection, orderBy);

        db = mDatabaseHelper.getReadableDatabase();
        Cursor c = db.rawQuery(query, selectionArgs);
        List<DictionaryModel> dictionaries = populate(c);
        c.close();
        db.close();

//        return Observable.create(subscriber -> subscriber.onNext(dictionaries));
        return Observable.from(dictionaries);
    }

    @Override
    public void save(@NonNull DictionaryModel dictionary) {
        ContentValues values = new ContentValues();
        values.put(DictionariesEntry.COLUMN_ENTRY_ID, dictionary.getId());
        values.put(DictionariesEntry.COLUMN_TITLE, dictionary.getTitle());

        db = mDatabaseHelper.getWritableDatabase();
        db.insert(DictionariesEntry.TABLE_NAME, null, values);
        db.close();
    }

    @Override
    public void update(@NonNull DictionaryModel model) {
        String whereClause = DictionariesEntry.COLUMN_ENTRY_ID + "= ?";
        String[] whereArgs = {model.getId()};

        ContentValues values = new ContentValues();
        values.put(DictionariesEntry.COLUMN_TITLE, model.getTitle());

        db = mDatabaseHelper.getWritableDatabase();
        db.update(DictionariesEntry.TABLE_NAME, values, whereClause, whereArgs);
        db.close();
    }

    @Override
    public void remove(@NonNull DictionaryModel dictionary) {
        String selection = DictionariesEntry.COLUMN_ENTRY_ID + " LIKE ?";
        String[] selectionArgs = {dictionary.getId()};

        db = mDatabaseHelper.getWritableDatabase();
        db.delete(DictionariesEntry.TABLE_NAME, selection, selectionArgs);
        db.close();
    }

    @Override
    public void remove(@NonNull List<DictionaryModel> entries) {
        List<String> ids = new ArrayList<>();
        for (DictionaryModel entry : entries) {
            ids.add(entry.getId());
        }
        String query = String.format("DELETE FROM " + DictionariesEntry.TABLE_NAME + " WHERE "
                + DictionariesEntry.COLUMN_ENTRY_ID + " IN (%s);", TextUtils.join(", ", ids));

        db = mDatabaseHelper.getWritableDatabase();
        db.execSQL(query);
        db.close();
    }

    @NonNull
    private String createSelectionQuery(@Nullable String selection, @Nullable String orderBy) {
        StringBuilder query = new StringBuilder();

        query.append("SELECT ").append(DictionariesEntry.COLUMN_ENTRY_ID).append(", ")
                .append(DictionariesEntry.COLUMN_TITLE)
                .append(" FROM ").append(DictionariesEntry.TABLE_NAME);

        if (selection != null && !selection.isEmpty()) {
            query.append(" WHERE ").append(selection);
        }
        if (orderBy != null && !orderBy.isEmpty()) {
            query.append(" ORDER BY ").append(orderBy);
        }

        return query.toString();
    }

    private List<DictionaryModel> populate(@NonNull Cursor c) {
        List<DictionaryModel> dictionaries = new ArrayList<>();
        if (c.getCount() > 0 && c.moveToFirst()) {
            do {
                String itemId = c.getString(c.getColumnIndexOrThrow(DictionariesEntry.COLUMN_ENTRY_ID));
                String title = c.getString(c.getColumnIndexOrThrow(DictionariesEntry.COLUMN_TITLE));
                DictionaryModel dictionary = new DictionaryModel(itemId, title);
                dictionaries.add(dictionary);
            } while (c.moveToNext());
        }
        return dictionaries;
    }

}
