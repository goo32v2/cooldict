package com.goo32v2.cooldict.model;

import com.goo32v2.cooldict.data.models.DictionaryModel;

import java.util.List;

/**
 * Created on 04-Jul-16. (c) CoolDict
 */

public interface WordManagerModelContract {

    List<String> getDictionaryNames();

    DictionaryModel getDictionaryByName(String id);

    void saveDictionary(DictionaryModel model);

    void create(String originalWord, String translatedWord, String dictionary);

    void update(String id, String originalText, String translatedText, String dictionary);

    DictionaryModel getDictionary(String dictionaryID);
}
