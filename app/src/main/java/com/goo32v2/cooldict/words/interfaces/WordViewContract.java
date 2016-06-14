package com.goo32v2.cooldict.words.interfaces;

import com.goo32v2.cooldict.BaseView;
import com.goo32v2.cooldict.data.models.WordModel;

import java.util.List;

/**
 * Created on 14-May-16. (c) CoolDict
 */

public interface WordViewContract extends BaseView<WordPresenterContract> {

    void setLoadingIndicator(boolean active);

    void showWords(List<WordModel> words);

    void showAddWord();

    void showWordDetailUi(WordModel word);

    void showMessage(String msg);

    void showError(String msg);

    void showNoWords();

    boolean isActive();
}
