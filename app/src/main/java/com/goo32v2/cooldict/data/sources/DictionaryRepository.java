package com.goo32v2.cooldict.data.sources;

import android.support.annotation.NonNull;

import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.sources.database.DatabasePersistenceContract;
import com.goo32v2.cooldict.data.sources.database.dao.DictionaryDao;
import com.goo32v2.cooldict.data.sources.interfaces.DictDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created on 14-May-16. (c) CoolDict
 */


public class DictionaryRepository implements DictDataSource {

    private static DictionaryRepository INSTANCE = null;
    private final DictionaryDao mDictionaryDao;

    private DictionaryRepository(@NonNull DictionaryDao dictionaryDao){
        this.mDictionaryDao = checkNotNull(dictionaryDao);
    }

    public static DictionaryRepository getInstance(DictionaryDao dictionaryDao) {
        if (INSTANCE == null)
            INSTANCE = new DictionaryRepository(dictionaryDao);
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

    @Override
    public void get(@NonNull GetListCallback<DictionaryModel> callback,
                    String selection,
                    String[] selectionArgs) {

        checkNotNull(callback);

        mDictionaryDao.get(callback, selection, selectionArgs);
    }

    @Override
    public void save(@NonNull DictionaryModel entry) {
        checkNotNull(entry);
        mDictionaryDao.save(entry);
    }

    @Override
    public void update(String id, DictionaryModel newModel) {
        mDictionaryDao.update(id, newModel);
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
    public void getDictionary(String id, @NonNull GetListCallback<DictionaryModel> callback){
        String selection = DatabasePersistenceContract.DictionaryEntry.COLUMN_ENTRY_ID + " LIKE ?";
        String[] selectionArgs= { id };
        get(callback, selection, selectionArgs);

    }

    @Override
    public void getDictionaryList(@NonNull GetListCallback<DictionaryModel> callback){
        get(callback, null, null);
    }
}
