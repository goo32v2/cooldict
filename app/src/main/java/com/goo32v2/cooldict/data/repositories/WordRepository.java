package com.goo32v2.cooldict.data.repositories;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.goo32v2.cooldict.data.daos.WordDao;
import com.goo32v2.cooldict.data.models.WordModel;

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
    public void getWordsList(@NonNull GetListCallback<WordModel> callback) {
        mWordDao.getWordsList(callback);
    }

    @Override
    public void getWordById(@NonNull String wordId, @NonNull GetListCallback<WordModel> callback) {
        mWordDao.getWordById(wordId, callback);
    }

    @Override
    public void getWordsByDictionary(@NonNull String dictionaryId, @NonNull GetListCallback<WordModel> callback) {
        mWordDao.getWordsByDictionary(dictionaryId, callback);
    }

    @Override
    public void get(@NonNull GetListCallback<WordModel> callback, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String orderBy) {
        mWordDao.get(callback, selection, selectionArgs, orderBy);
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
    public void removeAll() {
        mWordDao.removeAll();
    }

    @Override
    public void update(@NonNull WordModel model) {
        mWordDao.update(model);
    }
}
