package com.goo32v2.cooldict.data.models;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

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
    @Nullable private String dictionaryId;
    @Nullable private String dictionaryTitle;

    // crutch and bicycle i think
    @Nullable private View.OnClickListener onClickListener;


    /**
     * Use this constructor to create new Word.
     *
     * @param originalWord - Original word
     * @param translatedWord - Translated word
     * @param dictionaryId - DictionaryEntry
     */
    public WordModel(@NonNull String originalWord,
                     @Nullable String translatedWord,
                     @Nullable String dictionaryId) {
        this.id = UUID.randomUUID().toString();
        this.originalWord = originalWord;
        this.translatedWord = translatedWord;
        this.dictionaryId = dictionaryId;
    }

    /**
     * Use this constructor to create new Word.
     *
     * @param originalWord - Original word
     * @param translatedWord - Translated word
     * @param dictionary - DictionaryEntry
     */
    public WordModel(@NonNull String originalWord,
                     @Nullable String translatedWord,
                     @Nullable DictionaryModel dictionary) {
        this.id = UUID.randomUUID().toString();
        this.originalWord = originalWord;
        this.translatedWord = translatedWord;
        if (dictionary != null){
            this.dictionaryId = dictionary.getId();
            this.dictionaryTitle = dictionary.getTitle();
        }
    }

    /**
     * Use this constructor to populate Word from repository.
     *
     * @param mId - Id
     * @param originalWord - Original word
     * @param translatedWord - Translated word
     * @param dictionaryId - DictionaryEntry
     */
    public WordModel(@NonNull String mId,
                     @NonNull String originalWord,
                     @Nullable String translatedWord,
                     @Nullable String dictionaryId,
                     @Nullable String dictionaryTitle) {
        this.id = mId;
        this.originalWord = originalWord;
        this.translatedWord = translatedWord;
        this.dictionaryId = dictionaryId;
        this.dictionaryTitle = dictionaryTitle;
    }

    /**
     * Use this constructor to populate Word from repository.
     *
     * @param id - Id
     * @param originalWord - Original word
     * @param translatedWord - Translated word
     * @param dictionary - DictionaryEntry
     */
    public WordModel(@NonNull String id,
                     @NonNull String originalWord,
                     @Nullable String translatedWord,
                     @Nullable DictionaryModel dictionary) {
        this.id = id;
        this.originalWord = originalWord;
        this.translatedWord = translatedWord;
        if (dictionary != null){
            this.dictionaryId = dictionary.getId();
            this.dictionaryTitle = dictionary.getTitle();
        }
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
    public String getDictionaryId() {
        return dictionaryId;
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

    public void setDictionaryId(@Nullable String dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    @Nullable
    public String getDictionaryTitle() {
        return dictionaryTitle;
    }

    public void setDictionaryTitle(@Nullable String dictionaryTitle) {
        this.dictionaryTitle = dictionaryTitle;
    }

    @Nullable
    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(@Nullable View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
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
                Objects.equals(getDictionaryId(), wordModel.getDictionaryId());
    }
}
