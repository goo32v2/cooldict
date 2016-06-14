package com.goo32v2.cooldict.data.sources.interfaces;

import com.goo32v2.cooldict.data.models.WordModel;

/**
 * Created on 22-May-16. (c) CoolDict
 */

public interface WordDataSource extends DataSource<WordModel> {

    void getAllWordsByDictionary(String dictionaryID, GetListCallback<WordModel> callback);

    void removeAll();
}
