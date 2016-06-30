package com.goo32v2.cooldict.data.sources.cache;

import com.goo32v2.cooldict.data.models.Model;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created on 22-May-16. (c) CoolDict
 */

public class Cache<T extends Model> implements CacheApi<T> {

    private LinkedHashMap<String, T> cache;

    public Cache(){
        cache = new LinkedHashMap<>();
    }

    @Override
    public void add(List<T> entries) {
        for (T model : entries) {
            cache.put(model.getId(), model);
        }
    }

    @Override
    public void add(String id, T object) {
        cache.put(id, object);
    }

    @Override
    public Model get(String id) {
        return cache.get(id);
    }

    @Override
    public LinkedHashMap<String, T> getAll() {
        return cache;
    }

    @Override
    public boolean inCache(String id) {
        return get(id) != null;
    }

    @Override
    public void remove(String id) {
        cache.remove(id);
    }

    @Override
    public void invalidate() {
        cache.clear();
    }

    @Override
    public int size() {
        return cache.size();
    }
}
