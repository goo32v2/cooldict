package com.goo32v2.cooldict.addeditword;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.goo32v2.cooldict.addeditword.interfaces.AddEditWordPresenterContract;
import com.goo32v2.cooldict.addeditword.interfaces.AddEditWordViewContract;
import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.data.sources.DictionaryRepository;
import com.goo32v2.cooldict.data.sources.WordRepository;
import com.goo32v2.cooldict.data.sources.interfaces.DataSource;
import com.goo32v2.cooldict.data.sources.interfaces.WordDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created on 13-Jun-16. (c) CoolDict
 */

public class AddEditWordPresenter implements AddEditWordPresenterContract, DataSource.GetEntryCallback<WordModel>{

    @NonNull
    private final WordDataSource mWordRepository;

    @NonNull final DictionaryRepository mDictionaryRepository;

    @NonNull
    private final AddEditWordViewContract mAddWordView;

    @Nullable
    private String mWordId;

    private DictionaryModel activeDictionary;

    public AddEditWordPresenter(@Nullable String wordId,
                                @NonNull WordRepository wordRepository,
                                @NonNull DictionaryRepository dictionaryRepository,
                                @NonNull AddEditWordFragment addEditWordFragment) {
        mAddWordView = checkNotNull(addEditWordFragment);
        mWordRepository = checkNotNull(wordRepository);
        mDictionaryRepository = checkNotNull(dictionaryRepository);
        mWordId = wordId;

        mAddWordView.setPresenter(this);
    }

    @Override
    public void createWord(String originalWord, String translatedWord, String dictionaryTitle) {
        getDictionaryByTitle(dictionaryTitle);
        WordModel wordModel = new WordModel(
                originalWord,
                translatedWord,
                activeDictionary.getId());
        mWordRepository.save(wordModel);
        mAddWordView.showWordList();
    }

    @Override
    public void updateWord(String wordId, String originalWord, String translatedWord, String dictionary) {
        if (mWordId == null) {
            throw new RuntimeException("updateWord() was called but word is new");
        }
        getDictionaryByTitle(dictionary);
        WordModel wordModel = new WordModel(wordId, originalWord, translatedWord, activeDictionary.getId());
        mWordRepository.update(wordId, wordModel);
        mAddWordView.showWordList();
    }

    @Override
    public void populateWord() {
        if (mWordId == null) {
            throw new RuntimeException("populateWord() was called but word is new");
        }
        mWordRepository.get(mWordId, this);
    }

    @Override
    public void start() {
        if (mWordId != null) {
            populateWord();
        }
    }

    // TODO: 18-Jun-16 do something with this ugly code
    private void getDictionaryByTitle(final String dictionaryTitle){
        mDictionaryRepository.getByTitle(dictionaryTitle, new DataSource.GetEntryCallback<DictionaryModel>() {
            @Override
            public void onLoaded(DictionaryModel entry) {
                activeDictionary = entry;
            }

            @Override
            public void onDataNotAvailable() {
                activeDictionary = new DictionaryModel(dictionaryTitle);
                mDictionaryRepository.save(activeDictionary);
            }
        });
    }

    private void getDictionaryById(final String dictionaryId){
        mDictionaryRepository.get(dictionaryId, new DataSource.GetEntryCallback<DictionaryModel>() {
            @Override
            public void onLoaded(DictionaryModel entry) {
                activeDictionary = entry;
            }

            @Override
            public void onDataNotAvailable() {
            }
        });
    }

    @Override
    public void onLoaded(WordModel entry) {
        getDictionaryById(entry.getDictionaryID());
        if (mAddWordView.isActive()) {
            mAddWordView.setWordId(entry.getId());
            mAddWordView.setOriginalWord(entry.getOriginalWord());
            mAddWordView.setTranslatedWord(entry.getTranslatedWord());
            mAddWordView.setDictionary(activeDictionary.getTitle());
        }
    }

    @Override
    public void onDataNotAvailable() {
        if (mAddWordView.isActive()){
            mAddWordView.showEmptyWordError();
        }
    }
}
