package com.goo32v2.cooldict.data.repositories;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.goo32v2.cooldict.data.daos.DictionaryDao;
import com.goo32v2.cooldict.data.models.DictionaryModel;

/**
 * Created on 14-May-16. (c) CoolDict
 */


public class DictionaryRepository implements DictDataSource {

    private static DictionaryRepository INSTANCE = null;
    private final DictionaryDao mDictionaryDao;

    private DictionaryRepository(@NonNull DictionaryDao dictionaryDao){
        this.mDictionaryDao = dictionaryDao;
    }

    public static DictionaryRepository getInstance(DictionaryDao dictionaryDao) {
        if (INSTANCE == null)
            INSTANCE = new DictionaryRepository(dictionaryDao);
        return INSTANCE;
    }

    @Override
    public void getDictionaryList(@NonNull GetListCallback<DictionaryModel> callback) {
        mDictionaryDao.getDictionaryList(callback);
    }

    @Override
    public void getDictionaryById(String id, @NonNull GetListCallback<DictionaryModel> callback) {
        mDictionaryDao.getDictionaryById(id, callback);
    }

    @Override
    public void getDictionaryByName(String name, @NonNull GetListCallback<DictionaryModel> callback) {
        mDictionaryDao.getDictionaryByName(name, callback);
    }

    @Override
    public void get(@NonNull GetListCallback<DictionaryModel> callback, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String orderBy) {
        mDictionaryDao.get(callback, selection, selectionArgs, orderBy);
    }

    @Override
    public void save(@NonNull DictionaryModel entry) {
        mDictionaryDao.save(entry);
    }

    @Override
    public void remove(@NonNull DictionaryModel entry) {
        mDictionaryDao.remove(entry);
    }

    // TODO: 11-Jul-16 implement for words in dictionary
    @Override
    public void removeAll() {
        mDictionaryDao.removeAll();
    }

    @Override
    public void update(@NonNull DictionaryModel model) {
        mDictionaryDao.update(model);
    }
}
