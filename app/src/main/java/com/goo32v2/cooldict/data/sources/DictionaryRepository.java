package com.goo32v2.cooldict.data.sources;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.sources.cache.Cache;
import com.goo32v2.cooldict.data.sources.database.dao.DictionaryDao;
import com.goo32v2.cooldict.data.sources.interfaces.DictDataSource;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created on 14-May-16. (c) CoolDict
 */


public class DictionaryRepository implements DictDataSource {

    private static DictionaryRepository INSTANCE = null;
    private final DictionaryDao mDictionaryDao;
    private Cache<DictionaryModel> mCache;
    private static final String DEFAULT_DICTIONARY = SourcesConstants.DEFAULT_DICTIONARY_ID;

    private DictionaryRepository(@NonNull DictionaryDao dictionaryDao,
                                 @Nullable Cache<DictionaryModel> cache){
        this.mDictionaryDao = checkNotNull(dictionaryDao);
        this.mCache = cache != null ? cache : new Cache<DictionaryModel>();
    }

    public static DictionaryRepository getInstance(DictionaryDao dictionaryDao, Cache<DictionaryModel> cache) {
        if (INSTANCE == null)
            INSTANCE = new DictionaryRepository(dictionaryDao, cache);
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

    @Override
    public void get(@NonNull final GetListCallback<DictionaryModel> callback) {
        checkNotNull(callback);

        if (mCache.getAll() != null && mCache.size() != 0){
            callback.onLoaded(new ArrayList<>(mCache.getAll().values()));
            return;
        }

        mDictionaryDao.get(new GetListCallback<DictionaryModel>() {
            @Override
            public void onLoaded(List<DictionaryModel> entry) {
                mCache.init(entry);
                callback.onLoaded(entry);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void get(@NonNull String id, @NonNull final GetEntryCallback<DictionaryModel> callback) {
        checkNotNull(mDictionaryDao);
        checkNotNull(callback);

        DictionaryModel dictionary = (DictionaryModel) mCache.get(id);
        if (dictionary != null){
            callback.onLoaded(dictionary);
            return;
        }

        mDictionaryDao.get(id, new GetEntryCallback<DictionaryModel>() {
            @Override
            public void onLoaded(DictionaryModel entry) {
                callback.onLoaded(entry);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void save(@NonNull DictionaryModel entry) {
        checkNotNull(entry);
        mDictionaryDao.save(entry);
        mCache.push(entry.getId(), entry);
    }

    @Override
    public void remove(@NonNull String entry) {
        mDictionaryDao.remove(entry);
        mCache.remove(entry);
    }

    @Override
    public void remove(@NonNull DictionaryModel entry) {
        mDictionaryDao.remove(entry);
        mCache.remove(entry.getId());
    }

    @Override
    public void update(String id, DictionaryModel newModel) {
        mDictionaryDao.update(id, newModel);
    }

    @Override
    public void getDefaultDictionary(@NonNull final GetEntryCallback<DictionaryModel> callback) {
        if (mCache.get(DEFAULT_DICTIONARY) != null){
            callback.onLoaded((DictionaryModel) mCache.get(DEFAULT_DICTIONARY));
            return;
        }

        mDictionaryDao.get(DEFAULT_DICTIONARY, new GetEntryCallback<DictionaryModel>() {
            @Override
            public void onLoaded(DictionaryModel entry) {
                callback.onLoaded(entry);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void deleteAll() {
        mDictionaryDao.deleteAll();
    }
}
