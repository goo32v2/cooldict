package com.goo32v2.cooldict.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.goo32v2.cooldict.data.database.DatabasePersistenceContract.*;

/**
 * Created on 14-May-16. (c) CoolDict
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String LOG = "DatabaseHelper";
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "cooldict.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ", ";

    private static final String CREATE_TABLE_WORDS = "CREATE TABLE " + WordsEntry.TABLE_NAME + "("
            + WordsEntry.COLUMN_ENTRY_ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT NOT NULL"
            + COMMA_SEP + WordsEntry.COLUMN_ORIGINAL_WORD + TEXT_TYPE + COMMA_SEP
            + WordsEntry.COLUMN_TRANSLATED_WORD + TEXT_TYPE + COMMA_SEP
            + WordsEntry.COLUMN_DICTIONARY_ID + TEXT_TYPE + ")";

    private static final String CREATE_TABLE_DICTIONARY = "CREATE TABLE "
            + DictionariesEntry.TABLE_NAME + "(" + DictionariesEntry.COLUMN_ENTRY_ID + INTEGER_TYPE
            + " PRIMARY KEY AUTOINCREMENT NOT NULL" + COMMA_SEP
            + DictionariesEntry.COLUMN_TITLE + TEXT_TYPE + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i(LOG, "DatabaseHelper instance create");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(LOG, "Create Words table");
        db.execSQL(CREATE_TABLE_WORDS);

        Log.i(LOG, "Create Dictionaries table");
        db.execSQL(CREATE_TABLE_DICTIONARY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
