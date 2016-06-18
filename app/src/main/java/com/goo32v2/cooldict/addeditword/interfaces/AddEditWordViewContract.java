package com.goo32v2.cooldict.addeditword.interfaces;

import com.goo32v2.cooldict.BaseView;

/**
 * Created on 13-Jun-16. (c) CoolDict
 */

public interface AddEditWordViewContract extends BaseView<AddEditWordPresenterContract> {

    void showEmptyWordError();

    void showWordList();

    boolean isActive();

    void setWordId(String id);

    void setOriginalWord(String originalWord);

    void setTranslatedWord(String translatedWord);

    void setDictionary(String dictionary);
}
