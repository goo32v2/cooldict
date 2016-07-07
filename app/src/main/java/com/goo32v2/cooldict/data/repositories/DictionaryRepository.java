package com.goo32v2.cooldict.data.repositories;

import android.support.annotation.NonNull;

import com.goo32v2.cooldict.data.DictDataSource;
import com.goo32v2.cooldict.data.daos.DictionaryDao;
import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.sources.database.DatabasePersistenceContract;

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
    public void get(@NonNull GetListCallback<DictionaryModel> callback,
                    String selection,
                    String[] selectionArgs,
                    String orderBy,
                    String groupBy,
                    String having) {

        mDictionaryDao.get(callback, selection, selectionArgs, orderBy, groupBy, having);
    }

    @Override
    public void save(@NonNull DictionaryModel entry) {
        mDictionaryDao.save(entry);
    }

    @Override
    public void update(@NonNull DictionaryModel model) {
        mDictionaryDao.update(model);
    }

    @Override
    public void remove(@NonNull DictionaryModel entry) {
        mDictionaryDao.remove(entry);
    }

    @Override
    public void removeAll() {
        mDictionaryDao.removeAll();
    }

    @Override
    public void getDictionaryById(String id, @NonNull GetListCallback<DictionaryModel> callback){
        String selection = DatabasePersistenceContract.DictionaryEntry.COLUMN_ENTRY_ID + " LIKE ?";
        String[] selectionArgs= { id };
        get(callback, selection, selectionArgs, null, null, null);

    }

    @Override
    public void getDictionaryList(@NonNull GetListCallback<DictionaryModel> callback){
        String orderBy = DatabasePersistenceContract.DictionaryEntry.COLUMN_TITLE + " ASC";
        get(callback, null, null, orderBy, null, null);
    }

    @Override
    public void getDictionaryByName(String name, @NonNull GetListCallback<DictionaryModel> callback) {
        String selection = DatabasePersistenceContract.DictionaryEntry.COLUMN_TITLE + " LIKE ?";
        String[] selectionArgs= { name };
        get(callback, selection, selectionArgs, null, null, null);
    }
}
