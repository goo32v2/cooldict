package com.goo32v2.cooldict.worddetails;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.data.sources.WordRepository;
import com.goo32v2.cooldict.data.sources.interfaces.DataSource;
import com.goo32v2.cooldict.worddetails.interfaces.WordDetailPresenterContract;
import com.goo32v2.cooldict.worddetails.interfaces.WordDetailViewContract;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created on 15-Jun-16. (c) CoolDict
 */

public class WordDetailPresenter implements WordDetailPresenterContract{

    private final WordRepository mWordRepository;

    private final WordDetailViewContract mViewContract;

    @Nullable
    private String mWordId;

    public WordDetailPresenter(@NonNull WordRepository wordRepository,
                               @NonNull WordDetailViewContract viewContract,
                               @Nullable String wordId){
        this.mWordRepository = checkNotNull(wordRepository);
        this.mViewContract = checkNotNull(viewContract);
        this.mWordId = wordId;

        mViewContract.setPresenter(this);
    }

    @Override
    public void editWord() {
        if (mWordId != null) {
            mViewContract.showEditWord(mWordId);
        } else {
            mViewContract.showMissingWord();
        }
    }

    @Override
    public void deleteWord() {
        if (mWordId != null) {
            mWordRepository.remove(mWordId);
            mViewContract.showDeleteWord();
        } else {
            mViewContract.showMissingWord();
        }
    }

    @Override
    public void start() {
        if (mWordId == null) {
            mViewContract.showMissingWord();
            return;
        }

        mViewContract.showLoadingIndicator(true);
        mWordRepository.get(mWordId, new DataSource.GetEntryCallback<WordModel>() {
            @Override
            public void onLoaded(WordModel entry) {
                mViewContract.showLoadingIndicator(false);
                showWord(entry);
            }

            @Override
            public void onDataNotAvailable() {
                mViewContract.showMissingWord();
            }
        });
    }

    private void showWord(WordModel entry) {
        String originalWord = entry.getOriginalWord();
        String translatedWord = entry.getTranslatedWord();
        String dictionaryId = entry.getDictionaryID();

        if (originalWord != null && !originalWord.isEmpty()){
            mViewContract.showOriginalWord(originalWord);
        } else {
            mViewContract.hideOriginalWord();
        }

        if (translatedWord != null && !translatedWord.isEmpty()){
            mViewContract.showTranslatedWord(translatedWord);
        } else {
            mViewContract.hideTranslatedWord();
        }

        mViewContract.showDictionaryId(dictionaryId);
    }
}
