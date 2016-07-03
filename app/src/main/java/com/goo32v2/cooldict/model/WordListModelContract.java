package com.goo32v2.cooldict.model;

import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.models.WordModel;

import java.util.List;

/**
 * Created on 02-Jul-16. (c) CoolDict
 */

public interface WordListModelContract {

    List<WordModel> getWordListBy(String dictionary);

    List<DictionaryModel> getDictionaryList();

}
