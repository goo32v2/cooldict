package com.goo32v2.cooldict.data.sources.interfaces;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created on 14-May-16. (c) CoolDict
 */

// TODO: 23-May-16 remove all method needed? or delete only selected?
public interface DataSource<T> {

    // For list
    interface GetListCallback<T> {

        void onLoaded(List<T> entries);

        void onDataNotAvailable();
    }

    void get(@NonNull final GetListCallback<T> callback, String selection, String[] selectionArgs);

    void save(@NonNull T entry);

    void remove(@NonNull T entry);

    void update(String id, T newModel);
}
