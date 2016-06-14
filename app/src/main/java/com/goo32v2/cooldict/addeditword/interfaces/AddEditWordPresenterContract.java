package com.goo32v2.cooldict.addeditword.interfaces;

import com.goo32v2.cooldict.BasePresenter;

/**
 * Created on 13-Jun-16. (c) CoolDict
 */

public interface AddEditWordPresenterContract extends BasePresenter {

    void createWord(String originalWord, String translatedWord, String dictionaryId);

    void updateWord(String wordId, String originalWord, String translatedWord, String dictionaryId);

    void populateWord();
}
