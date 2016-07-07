package com.goo32v2.cooldict.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created on 14-May-16. (c) CoolDict
 */

public interface DataSource<T> {

    interface GetListCallback<T> {

        void onLoaded(List<T> entries);

        void onDataNotAvailable();
    }

    void get(@NonNull GetListCallback<T> callback, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String orderBy);

    void save(@NonNull T entry);

    void remove(@NonNull T entry);

    void removeAll();

    void update(@NonNull T model);
}
