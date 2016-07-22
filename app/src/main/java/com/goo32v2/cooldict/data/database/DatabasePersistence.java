package com.goo32v2.cooldict.data.database;

import android.provider.BaseColumns;

/**
 * Created on 14-May-16. (c) CoolDict
 */

public class DatabasePersistence {
    public DatabasePersistence(){}

    public static abstract class WordsEntry implements BaseColumns{
        public static final String TABLE_NAME = "words";
        public static final String COLUMN_ENTRY_ID = "entry_id";
        public static final String COLUMN_ORIGINAL_WORD = "original_word";
        public static final String COLUMN_TRANSLATED_WORD = "translated_word";
        public static final String COLUMN_DICTIONARY_ID = "dictionary_id";
        public static final String COLUMN_CREATION_DATE = "created_at";
    }

    public static abstract class DictionariesEntry implements BaseColumns{
        public static final String TABLE_NAME = "dictionary";
        public static final String COLUMN_ENTRY_ID = "entry_id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_CREATION_DATE = "created_at";
    }
}
