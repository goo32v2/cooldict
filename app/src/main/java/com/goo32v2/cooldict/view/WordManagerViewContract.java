package com.goo32v2.cooldict.view;

import java.util.List;

/**
 * Created on 26-Jun-16. (c) CoolDict
 */

public interface WordManagerViewContract extends BaseView {

    void showMessage(String msg);

    void finishActivity();

    void populateWord(String id, String originalWord, String translatedWrd, String dictionary);

    void submit();

    void setupDictionaryAdapter(List<String> dictionaryNames);
}
