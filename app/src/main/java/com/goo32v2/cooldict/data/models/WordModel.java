package com.goo32v2.cooldict.data.models;

import android.support.annotation.Nullable;

import com.goo32v2.cooldict.Constants;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Created on 14-May-16. (c) CoolDict
 */

// TODO: 06-Jul-16 make model mutable
public final class WordModel implements BaseModel, Serializable{

    private final String mId;
    @Nullable private final String mOriginalWord;
    @Nullable private final String mTranslatedWord;
    @Nullable private final String mDictionary;


    /**
     * Use this constructor to create new Word.
     *
     * @param mOriginalWord - Original word
     * @param mTranslatedWord - Translated word
     * @param mDictionary - DictionaryEntry
     */
    public WordModel(@Nullable String mOriginalWord, @Nullable String mTranslatedWord,
                     @Nullable String mDictionary) {
        this.mId = UUID.randomUUID().toString();
        this.mOriginalWord = mOriginalWord;
        this.mTranslatedWord = mTranslatedWord;
        this.mDictionary = mDictionary;
    }

    /**
     * Use this constructor to copy Word from another.
     *
     * @param mId - Id
     * @param mOriginalWord - Original word
     * @param mTranslatedWord - Translated word
     * @param mDictionary - DictionaryEntry
     */
    public WordModel(String mId, @Nullable String mOriginalWord, @Nullable String mTranslatedWord,
                     @Nullable String mDictionary) {
        this.mId = mId;
        this.mOriginalWord = mOriginalWord;
        this.mTranslatedWord = mTranslatedWord;
        this.mDictionary = mDictionary;
    }

    /**
     * Use this constructor to create new Word in default dictionary.
     *
     * @param mOriginalWord - Original word
     * @param mTranslatedWord - Translated word
     */
    public WordModel(@Nullable String mOriginalWord, @Nullable String mTranslatedWord) {
        this.mId = UUID.randomUUID().toString();
        this.mOriginalWord = mOriginalWord;
        this.mTranslatedWord = mTranslatedWord;
        this.mDictionary = Constants.DEFAULT_DICTIONARY_ID;
    }

    @Override
    public String getId() {
        return mId;
    }

    @Nullable public String getOriginalWord() {
        return mOriginalWord;
    }

    @Nullable public String getTranslatedWord() {
        return mTranslatedWord;
    }

    @Nullable public String getDictionaryID() {
        return mDictionary;
    }

    @Override public String toString() {
        return getId();
    }

    @Override public int hashCode() {
        return Objects.hashCode(this);
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordModel wordModel = (WordModel) o;
        return Objects.equals(getId(), wordModel.getId()) &&
                Objects.equals(getOriginalWord(), wordModel.getOriginalWord()) &&
                Objects.equals(getTranslatedWord(), wordModel.getTranslatedWord()) &&
                Objects.equals(getDictionaryID(), wordModel.getDictionaryID());
    }
}
