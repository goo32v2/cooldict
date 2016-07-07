package com.goo32v2.cooldict.data.repositories;

import android.support.annotation.NonNull;

import com.goo32v2.cooldict.data.WordDataSource;
import com.goo32v2.cooldict.data.daos.WordDao;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.data.sources.database.DatabasePersistenceContract;

/**
 * Created on 17-May-16. (c) CoolDict
 */

public class WordRepository implements WordDataSource {

    private static WordRepository INSTANCE = null;
    private final WordDao mWordDao;

    private WordRepository(@NonNull WordDao wordDao){
        this.mWordDao = wordDao;
    }

    public static WordRepository getInstance(WordDao wordDao){
        if (INSTANCE == null)
            INSTANCE = new WordRepository(wordDao);
        return INSTANCE;
    }

    @Override
    public void removeAll() {
        mWordDao.removeAll();
    }

    @Override
    public void get(@NonNull GetListCallback<WordModel> callback,
                    String selection,
                    String[] selectionArgs,
                    String orderBy,
                    String groupBy,
                    String having) {

        mWordDao.get(callback, selection, selectionArgs, orderBy, groupBy, having);
    }

    @Override
    public void save(@NonNull WordModel entry) {
        mWordDao.save(entry);
    }

    @Override
    public void remove(@NonNull WordModel entry) {
        mWordDao.remove(entry);
    }

    @Override
    public void update(@NonNull WordModel model) {
        mWordDao.update(model);
    }

    @Override
    public void getWordById(String id, final @NonNull GetListCallback<WordModel> callback){
        String selection = DatabasePersistenceContract.WordsEntry.COLUMN_ENTRY_ID + " LIKE ?";
        String[] selectionArgs= { id };
        get(callback, selection, selectionArgs, null, null, null);
    }

    @Override
    public void getWordsList(final @NonNull GetListCallback<WordModel> callback){
        get(callback, null, null, null, null, null);
    }

    @Override
    public void getWordsByDictionary(String id, final @NonNull GetListCallback<WordModel> callback){
        String selection = DatabasePersistenceContract.WordsEntry.COLUMN_DICTIONARY_ID + " LIKE ?";
        String[] selectionArgs= { id };
        get(callback, selection, selectionArgs, null, null, null);
    }

    @Override
    public void getWordsListWithDictionaryData(@NonNull GetListCallback<WordModel> callback) {
        String query = "SELECT w." + DatabasePersistenceContract.WordsEntry.COLUMN_ENTRY_ID + ", " +
                "w." + DatabasePersistenceContract.WordsEntry.COLUMN_ORIGINAL_WORD + ", " +
                "w." + DatabasePersistenceContract.WordsEntry.COLUMN_TRANSLATED_WORD +  ", " +
                "d." + DatabasePersistenceContract.DictionaryEntry.COLUMN_ENTRY_ID +  ", " +
                "d." + DatabasePersistenceContract.DictionaryEntry.COLUMN_TITLE +
                " FROM " + DatabasePersistenceContract.WordsEntry.TABLE_NAME + " AS w INNER JOIN " +
                DatabasePersistenceContract.DictionaryEntry.TABLE_NAME + " AS d ON " +
                "w." + DatabasePersistenceContract.DictionaryEntry.COLUMN_ENTRY_ID +
                "=w." + DatabasePersistenceContract.WordsEntry.COLUMN_ENTRY_ID;

        mWordDao.get(callback, query);
    }
}
