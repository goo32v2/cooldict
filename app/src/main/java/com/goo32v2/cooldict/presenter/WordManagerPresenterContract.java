package com.goo32v2.cooldict.presenter;

import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.view.WordManagerViewContract;

import java.util.List;

/**
 * Created on 26-Jun-16. (c) CoolDict
 */

public interface WordManagerPresenterContract extends BasePresenter<WordManagerViewContract> {

    List<String> getDictionaryNames();

    void create(String originalWord, String translatedWord, String dictionaryId);

    void update(String id, String originalText, String translatedText, String dictionary);

    void showMessage(String msg);

    void populate(WordModel wordModel);

    void start(WordModel model);

    void formSubmit(String wordId, String originalText, String translatedText, String dictionary);
}
