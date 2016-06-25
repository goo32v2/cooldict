package com.goo32v2.cooldict.addeditword.interfaces;

import com.goo32v2.cooldict.BaseView;

/**
 * Created on 13-Jun-16. (c) CoolDict
 */

public interface AddEditWordViewContract extends BaseView<AddEditWordPresenterContract> {

    void setupAddView();

    void setupEditView();

    void showMessage(String msg);

    void finishActivity();

    interface Fragment {

        void setupAddView();

        void setupEditView();

        void setWordId(String id);

        void setOriginalWord(String originalWord);

        void setTranslatedWord(String translatedWord);

        void setDictionary(String dictionary);

    }
}
