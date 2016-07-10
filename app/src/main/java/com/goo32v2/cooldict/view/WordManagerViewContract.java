package com.goo32v2.cooldict.view;

import com.goo32v2.cooldict.data.models.WordModel;

import java.util.List;

/**
 * Created on 26-Jun-16. (c) CoolDict
 */

public interface WordManagerViewContract extends BaseView {

    void showMessage(String msg);

    void finishActivity();

    void populateWord(WordModel model);

    void submit();

    void setupDictionaryAdapter(List<String> dictionaryNames);
}
