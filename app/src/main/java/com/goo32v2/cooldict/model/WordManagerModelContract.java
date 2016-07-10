package com.goo32v2.cooldict.model;

import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.models.WordModel;

import java.util.List;

/**
 * Created on 04-Jul-16. (c) CoolDict
 */

public interface WordManagerModelContract {

    List<String> getDictionaryNames();

    DictionaryModel getDictionaryByName(String name);

    void saveDictionary(DictionaryModel model);

    void create(WordModel model);

    void update(WordModel model);

//    DictionaryModel getDictionary(String dictionaryID);
}
