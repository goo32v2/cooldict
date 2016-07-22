package com.goo32v2.cooldict.models;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created on 14-May-16. (c) CoolDict
 */

public final class DictionaryModel implements Serializable{

    @NonNull private String id;
    @NonNull private String title;

    /**
     * Use this constructor to populate dictionary
     *
     * @param id - id
     * @param title - dictionary title
     */
    public DictionaryModel(@NonNull String id, @NonNull String title) {
        this.id = id;
        this.title = title;
    }

    /**
     * Use this constructor to create new dictionary.
     *
     * @param title - dictionary title
     */
    public DictionaryModel(@NonNull String title) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "DictionaryModel{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DictionaryModel that = (DictionaryModel) o;

        if (!getId().equals(that.getId())) return false;
        return getTitle().equals(that.getTitle());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getTitle().hashCode();
        return result;
    }
}
