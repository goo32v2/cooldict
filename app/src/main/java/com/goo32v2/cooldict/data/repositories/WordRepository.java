package com.goo32v2.cooldict.data.repositories;

import android.support.annotation.NonNull;

import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.data.sources.database.DatabasePersistenceContract;
import com.goo32v2.cooldict.data.daos.WordDao;
import com.goo32v2.cooldict.data.WordDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created on 17-May-16. (c) CoolDict
 */

public class WordRepository implements WordDataSource {

    private static WordRepository INSTANCE = null;
    private final WordDao mWordDao;

    private WordRepository(@NonNull WordDao wordDao){
        this.mWordDao = checkNotNull(wordDao);
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
    public void get(@NonNull final GetListCallback<WordModel> callback,
                    String selection,
                    String[] selectionArgs) {

        checkNotNull(callback);

        mWordDao.get(callback, selection, selectionArgs);
    }

    @Override
    public void save(@NonNull WordModel entry) {
        checkNotNull(entry);
        mWordDao.save(entry);
    }

    @Override
    public void remove(@NonNull WordModel entry) {
        mWordDao.remove(entry);
    }

    @Override
    public void update(String id, WordModel newModel) {
        mWordDao.update(id, newModel);
    }

    @Override
    public void getWord(String id, final @NonNull GetListCallback<WordModel> callback){
        String selection = DatabasePersistenceContract.WordsEntry.COLUMN_ENTRY_ID + " LIKE ?";
        String[] selectionArgs= { id };
        get(callback, selection, selectionArgs);
    }

    @Override
    public void getWordsList(final @NonNull GetListCallback<WordModel> callback){
        get(callback, null, null);
    }

    @Override
    public void getWordsByDictionary(String id, final @NonNull GetListCallback<WordModel> callback){
        String selection = DatabasePersistenceContract.WordsEntry.COLUMN_DICTIONARY_ID + " LIKE ?";
        String[] selectionArgs= { id };
        get(callback, selection, selectionArgs);
    }
}
