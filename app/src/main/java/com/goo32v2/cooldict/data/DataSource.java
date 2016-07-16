package com.goo32v2.cooldict.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import rx.Observable;

/**
 * Created on 14-May-16. (c) CoolDict
 */

public interface DataSource<T> {

    Observable<T> get(@Nullable String selection, @Nullable String[] selectionArgs, @Nullable String orderBy);

    void save(@NonNull T entry);

    void remove(@NonNull T entry);

    void remove(@NonNull List<T> entries);

    void update(@NonNull T model);
}
