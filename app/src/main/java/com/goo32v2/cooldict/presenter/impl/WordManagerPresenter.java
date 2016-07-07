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
    public void create(String originalWord, String translatedWord, String dictionary) {
        mModel.create(originalWord, translatedWord, dictionary);
        mView.finishActivity();
    }

    @Override
    public void update(String id, String originalText, String translatedText, String dictionary) {
        mModel.update(id, originalText, translatedText, dictionary);
        mView.finishActivity();
    }

    // TODO: 04-Jul-16 separate
    @Override
    public void populate(WordModel wordModel) {
        DictionaryModel dictionary = mModel.getDictionary(wordModel.getDictionaryId());

        if (dictionary != null){
            mView.populateWord(
                    wordModel.getId(),
                    wordModel.getOriginalWord(),
                    wordModel.getTranslatedWord(),
                    dictionary.getTitle()
            );
        } else {
            mView.populateWord(
                    wordModel.getId(),
                    wordModel.getOriginalWord(),
                    wordModel.getTranslatedWord(),
                    ""
            );
        }
    }

    @Override
    public void formSubmit(String wordId, String originalText, String translatedText, String dictionary) {
        if (activeWord != null) {
            update(wordId, originalText, translatedText, dictionary);
        } else {
            create(originalText, translatedText, dictionary);
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
