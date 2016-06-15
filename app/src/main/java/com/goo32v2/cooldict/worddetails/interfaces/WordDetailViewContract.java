package com.goo32v2.cooldict.worddetails.interfaces;

import com.goo32v2.cooldict.BaseView;

/**
 * Created on 15-Jun-16. (c) CoolDict
 */

public interface WordDetailViewContract extends BaseView<WordDetailPresenterContract> {

    void showLoadingIndicator(boolean active);

    boolean isActive();

    void showEditWord(String id);

    void showDeleteWord();

    void showMissingWord();

    void showOriginalWord(String entry);

    void hideOriginalWord();

    void showTranslatedWord(String entry);

    void hideTranslatedWord();

    void showDictionaryId(String dictionaryId);
}
