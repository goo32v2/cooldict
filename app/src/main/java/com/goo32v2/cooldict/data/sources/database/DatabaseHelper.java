package com.goo32v2.cooldict.data.sources.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.goo32v2.cooldict.Constants;

/**
 * Created on 14-May-16. (c) CoolDict
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String LOG = "DatabaseHelper";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "words.db";
    public static final String TEXT_TYPE = " TEXT";
    public static final String COMMA_SEP = ",";

    public static final String CREATE_TABLE_WORD = "CREATE TABLE " + DatabasePersistenceContract.WordsEntry.TABLE_NAME
            + "(" + DatabasePersistenceContract.WordsEntry.COLUMN_ENTRY_ID + TEXT_TYPE + " PRIMARY KEY" + COMMA_SEP
            + DatabasePersistenceContract.WordsEntry.COLUMN_ORIGINAL_WORD + TEXT_TYPE + COMMA_SEP
            + DatabasePersistenceContract.WordsEntry.COLUMN_TRANSLATED_WORD + TEXT_TYPE + COMMA_SEP
            + DatabasePersistenceContract.WordsEntry.COLUMN_DICTIONARY_ID + TEXT_TYPE + ")";

    public static final String CREATE_TABLE_DICTIONARY = "CREATE TABLE " + DatabasePersistenceContract.DictionaryEntry.TABLE_NAME
            + "(" + DatabasePersistenceContract.DictionaryEntry.COLUMN_ENTRY_ID + TEXT_TYPE + " PRIMARY KEY" + COMMA_SEP
            + DatabasePersistenceContract.DictionaryEntry.COLUMN_TITLE + TEXT_TYPE + ")";

    public static final String CREATE_DEFAULT_DICTIONARY = "INSERT INTO " +
            DatabasePersistenceContract.DictionaryEntry.TABLE_NAME + " VALUES ('" +
            Constants.DEFAULT_DICTIONARY_ID + "', '" + Constants.DEFAULT_DICTIONARY_NAME + "')";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i(LOG, "DatabaseHelper instance create");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(LOG, "Exec: " + CREATE_TABLE_WORD);
        db.execSQL(CREATE_TABLE_WORD);

        Log.i(LOG, "Exec: " + CREATE_TABLE_DICTIONARY);
        db.execSQL(CREATE_TABLE_DICTIONARY);

        Log.i(LOG, "Exec: " + CREATE_DEFAULT_DICTIONARY);
        db.execSQL(CREATE_DEFAULT_DICTIONARY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
