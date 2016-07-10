package com.goo32v2.cooldict.presenter.impl;

import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.model.impl.WordManagerModel;
import com.goo32v2.cooldict.presenter.WordManagerPresenterContract;
import com.goo32v2.cooldict.view.WordManagerViewContract;

import java.util.List;

/**
 * Created on 26-Jun-16. (c) CoolDict
 */

public class WordManagerPresenter implements WordManagerPresenterContract {

    private WordManagerViewContract mView;
    private WordManagerModel mModel;
    private WordModel activeWord;

    public WordManagerPresenter(WordManagerModel model) {
        this.mModel = model;
    }


    @Override
    public List<String> getDictionaryNames() {
        return mModel.getDictionaryNames();
    }

    @Override
    public void showMessage(String msg) {
        mView.showMessage(msg);
    }

    @Override
    public void create(WordModel model) {
        mModel.create(model);
        mView.finishActivity();
    }

    @Override
    public void update(WordModel model) {
        mModel.update(model);
        mView.finishActivity();
    }

    @Override
    public void populate(WordModel wordModel) {
            mView.populateWord(wordModel);
    }

    @Override
    public void formSubmit(String wordId, String originalText, String translatedText, String dictionary) {
        DictionaryModel dictionaryModel = mModel.getDictionaryByName(dictionary);
        if (activeWord != null) {
            activeWord.setId(wordId);
            activeWord.setOriginalWord(originalText);
            activeWord.setTranslatedWord(translatedText);
            if (activeWord.getDictionaryTitle() != null &&
                    !activeWord.getDictionaryTitle().equals(dictionary) &&
                    dictionaryModel != null){
                activeWord.setDictionaryId(dictionaryModel.getId());
                activeWord.setDictionaryTitle(dictionaryModel.getTitle());
            }
            update(activeWord);
        } else {
            create(new WordModel(originalText, translatedText, dictionaryModel));
        }
    }


    @Override
    public void start(WordModel model) {
        activeWord = model;
        if (activeWord != null) {
            populate(model);
        }

        mView.setupDictionaryAdapter(getDictionaryNames());
    }

    @Override
    public void start() {}

    @Override
    public void setView(WordManagerViewContract view) {
        this.mView = view;
    }
}
