package com.goo32v2.cooldict.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.goo32v2.cooldict.data.daos.DataSource;

import java.util.List;

import rx.Observable;

/**
 * Created on 22-Jul-16. (c) CoolDict
 */

abstract class AbstractRepository<MODEL> implements DataSource<MODEL> {

    private DataSource<MODEL> mDao;

    AbstractRepository(DataSource<MODEL> mDao) {
        this.mDao = mDao;
    }

    @Override
    public Observable<MODEL> get(@Nullable String selection,
                                 @Nullable String[] selectionArgs,
                                 @Nullable String orderBy) {
        return mDao.get(selection, selectionArgs, orderBy);
    }

    @Override
    public void save(@NonNull MODEL entry) {
        mDao.save(entry);
    }

    @Override
    public void remove(@NonNull MODEL entry) {
        mDao.remove(entry);
    }

    @Override
    public void remove(@NonNull List<MODEL> entries) {
        mDao.remove(entries);
    }

    @Override
    public void update(@NonNull MODEL model) {
        mDao.update(model);
    }

    abstract public Observable<MODEL> getOrdered();

    abstract public Observable<MODEL> getById(@NonNull String id);
}
