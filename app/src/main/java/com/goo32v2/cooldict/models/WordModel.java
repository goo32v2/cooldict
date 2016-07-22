package com.goo32v2.cooldict.models;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created on 14-May-16. (c) CoolDict
 */

public final class WordModel implements Serializable {

    @NonNull private String id;
    @NonNull private String originalWord;
    @Nullable private String translatedWord;
    @NonNull private DictionaryModel dictionary;

    /**
     * Use for mapping data
     *
     * @param id - word id
     * @param originalWord - original word title
     * @param translatedWord - translated word title, can be null
     * @param dictionary - dictionary model, that assign with word
     */
    public WordModel(@NonNull String id, @NonNull String originalWord,
                     @Nullable String translatedWord, @NonNull DictionaryModel dictionary) {
        this.id = id;
        this.originalWord = originalWord;
        this.translatedWord = translatedWord;
        this.dictionary = dictionary;
    }

    /**
     * Use for create new word
     *
     * @param originalWord - original word title
     * @param translatedWord - translated word title, can be null
     * @param dictionary - dictionary model, that assign with word
     */
    public WordModel(@NonNull String originalWord, @Nullable String translatedWord,
                     @NonNull DictionaryModel dictionary) {
        this.id = UUID.randomUUID().toString();
        this.originalWord = originalWord;
        this.translatedWord = translatedWord;
        this.dictionary = dictionary;
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

    @NonNull
    public DictionaryModel getDictionary() {
        return dictionary;
    }

    @NonNull
    public String getDictionaryId() {
        return dictionary.getId();
    }

    @NonNull
    public String getDictionaryTitle() {
        return dictionary.getTitle();
    }

    @Override
    public String toString() {
        return "WordModel{" +
                "id='" + id + '\'' +
                ", originalWord='" + originalWord + '\'' +
                ", translatedWord='" + translatedWord + '\'' +
                ", dictionary=" + dictionary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WordModel wordModel = (WordModel) o;

        if (!getId().equals(wordModel.getId())) return false;
        if (!getOriginalWord().equals(wordModel.getOriginalWord())) return false;
        if (getTranslatedWord() != null ? !getTranslatedWord().equals(wordModel.getTranslatedWord()) : wordModel.getTranslatedWord() != null)
            return false;
        return getDictionary().equals(wordModel.getDictionary());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getOriginalWord().hashCode();
        result = 31 * result + (getTranslatedWord() != null ? getTranslatedWord().hashCode() : 0);
        result = 31 * result + getDictionary().hashCode();
        return result;
    }
}