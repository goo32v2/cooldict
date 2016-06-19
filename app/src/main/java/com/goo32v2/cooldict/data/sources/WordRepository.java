package com.goo32v2.cooldict.data.sources;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.data.sources.cache.Cache;
import com.goo32v2.cooldict.data.sources.database.dao.WordDao;
import com.goo32v2.cooldict.data.sources.interfaces.WordDataSource;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created on 17-May-16. (c) CoolDict
 */

public class WordRepository implements WordDataSource {

    private static WordRepository INSTANCE = null;
    private final WordDao mWordDao;
    private Cache<WordModel> mCache;

    private WordRepository(@NonNull WordDao wordDao, @Nullable Cache<WordModel> cache){
        this.mWordDao = checkNotNull(wordDao);
        this.mCache = cache != null ? cache : new Cache<WordModel>();
    }

    public static WordRepository getInstance(WordDao wordDao, Cache<WordModel> cache){
        if (INSTANCE == null)
            INSTANCE = new WordRepository(wordDao, cache);
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

    @Override
    public void removeAll() {
        mWordDao.removeAll();
    }

    @Override
    public void get(@NonNull final GetListCallback<WordModel> callback) {
        checkNotNull(callback);

        if (mCache.getAll() != null && mCache.size() != 0){
            callback.onLoaded(new ArrayList<>(mCache.getAll().values()));
            return;
        }

        mWordDao.get(new GetListCallback<WordModel>() {
            @Override
            public void onLoaded(List<WordModel> entry) {
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
    public void get(@NonNull String wordId, @NonNull final GetEntryCallback<WordModel> callback) {
        checkNotNull(wordId);
        checkNotNull(callback);

        WordModel word = (WordModel) mCache.get(wordId);
        if (word != null){
            callback.onLoaded(word);
            return;
        }

        mWordDao.get(wordId, new GetEntryCallback<WordModel>() {
            @Override
            public void onLoaded(WordModel entry) {
                callback.onLoaded(entry);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });

    }

    @Override
    public void get(final @NonNull String dictionaryId, final @NonNull GetListCallback<WordModel> callback) {
        checkNotNull(callback);

        mWordDao.get(dictionaryId, new GetListCallback<WordModel>() {
            @Override
            public void onLoaded(List<WordModel> entry) {
                callback.onLoaded(entry);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void save(@NonNull WordModel entry) {
        checkNotNull(entry);
        mWordDao.save(entry);
        mCache.push(entry.getId(), entry);
    }

    @Override
    public void remove(@NonNull String entry) {
        mWordDao.remove(entry);
        mCache.remove(entry);
    }

    @Override
    public void remove(@NonNull WordModel entry) {
        mWordDao.remove(entry);
        mCache.remove(entry.getId());
    }

    @Override
    public void update(String id, WordModel newModel) {
        mWordDao.update(id, newModel);
        mCache.remove(id);
        mCache.push(id, newModel);
    }
}
