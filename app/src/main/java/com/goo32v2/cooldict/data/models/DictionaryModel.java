package com.goo32v2.cooldict.data.models;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Created on 14-May-16. (c) CoolDict
 */

public final class DictionaryModel implements BaseModel, Serializable{

    @NonNull private String id;
    @NonNull private String title;

    /**
     * Use this constructor to populate dictionary from repository.
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

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    @Override
    public String toString() {
        return getId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DictionaryModel dictionaryModel = (DictionaryModel) o;
        return Objects.equals(getId(), dictionaryModel.getId()) &&
                Objects.equals(getTitle(), dictionaryModel.getTitle());
    }
}
