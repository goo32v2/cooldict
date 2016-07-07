package com.goo32v2.cooldict.data.models;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Created on 14-May-16. (c) CoolDict
 */

public final class WordModel implements BaseModel, Serializable{

    @NonNull private String id;
    @NonNull private String originalWord;
    @Nullable private String translatedWord;
    @Nullable private String dictionary;
    @Nullable private String dictionaryTitle;


    /**
     * Use this constructor to create new Word.
     *
     * @param originalWord - Original word
     * @param translatedWord - Translated word
     * @param dictionary - DictionaryEntry
     */
    public WordModel(@NonNull String originalWord, @Nullable String translatedWord,
                     @Nullable String dictionary) {
        this.id = UUID.randomUUID().toString();
        this.originalWord = originalWord;
        this.translatedWord = translatedWord;
        this.dictionary = dictionary;
    }

    /**
     * Use this constructor to populate Word from repository.
     *
     * @param mId - Id
     * @param originalWord - Original word
     * @param translatedWord - Translated word
     * @param dictionary - DictionaryEntry
     */
    public WordModel(@NonNull String mId, @NonNull String originalWord, @Nullable String translatedWord,
                     @Nullable String dictionary) {
        this.id = mId;
        this.originalWord = originalWord;
        this.translatedWord = translatedWord;
        this.dictionary = dictionary;
    }

    public WordModel(@NonNull String id,
                     @NonNull String originalWord,
                     @Nullable String translatedWord,
                     @Nullable String dictionary,
                     @Nullable String dictionaryTitle) {
        this.id = id;
        this.originalWord = originalWord;
        this.translatedWord = translatedWord;
        this.dictionary = dictionary;
        this.dictionaryTitle = dictionaryTitle;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getOriginalWord() {
        return originalWord;
    }

    @Nullable
    public String getTranslatedWord() {
        return translatedWord;
    }

    @Nullable
    public String getDictionary() {
        return dictionary;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setOriginalWord(@NonNull String originalWord) {
        this.originalWord = originalWord;
    }

    public void setTranslatedWord(@Nullable String translatedWord) {
        this.translatedWord = translatedWord;
    }

    public void setDictionary(@Nullable String dictionary) {
        this.dictionary = dictionary;
    }

    @Nullable
    public String getDictionaryTitle() {
        return dictionaryTitle;
    }

    public void setDictionaryTitle(@Nullable String dictionaryTitle) {
        this.dictionaryTitle = dictionaryTitle;
    }

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
        WordModel wordModel = (WordModel) o;
        return Objects.equals(getId(), wordModel.getId()) &&
                Objects.equals(getOriginalWord(), wordModel.getOriginalWord()) &&
                Objects.equals(getTranslatedWord(), wordModel.getTranslatedWord()) &&
                Objects.equals(getDictionary(), wordModel.getDictionary());
    }
}
