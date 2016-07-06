package com.goo32v2.cooldict.data.models;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Created on 14-May-16. (c) CoolDict
 */

// TODO: 06-Jul-16 make model mutable
public final class DictionaryModel implements BaseModel, Serializable{

    private final String mId;
    @NonNull private final String mTitle;

    /**
     * Use this constructor to copy dictionary from another dictionary.
     *
     * @param mId - id
     * @param mTitle - dictionary title
     */
    public DictionaryModel(String mId, @NonNull String mTitle) {
        this.mId = mId;
        this.mTitle = mTitle;
    }

    /**
     * Use this constructor to create new dictionary.
     *
     * @param mTitle - dictionary title
     */
    public DictionaryModel(@NonNull String mTitle) {
        this.mId = UUID.randomUUID().toString();
        this.mTitle = mTitle;
    }

    @Override
    public String getId() {
        return mId;
    }

    @NonNull public String getTitle() {
        return mTitle;
    }


    @NonNull @Override public String toString() {
        return getId();
    }

    @Override public int hashCode() {
        return Objects.hashCode(this);
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DictionaryModel dictionaryModel = (DictionaryModel) o;
        return Objects.equals(getId(), dictionaryModel.getId()) &&
                Objects.equals(getTitle(), dictionaryModel.getTitle());
    }
}
