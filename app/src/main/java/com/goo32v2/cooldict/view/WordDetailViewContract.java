package com.goo32v2.cooldict.view;

import com.goo32v2.cooldict.data.models.WordModel;

/**
 * Created on 15-Jun-16. (c) CoolDict
 */

public interface WordDetailViewContract extends BaseView {

//    void populate(WordWithDictionaryDTO<WordModel, DictionaryModel> dto);
    void populate(WordModel model);

    void finishActivity();

    WordModel getExtraWord();

}
