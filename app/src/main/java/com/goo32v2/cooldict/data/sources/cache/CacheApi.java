package com.goo32v2.cooldict.data.sources.cache;

import com.goo32v2.cooldict.data.models.Model;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created on 22-May-16. (c) CoolDict
 */

public interface CacheApi<T> {

    void add(List<T> entries);

    void add(String id, T object);

    Model get(String id);

    LinkedHashMap<String, T> getAll();

    boolean inCache(String id);

    void remove(String id);

    void invalidate();

    int size();

}
