package com.goo32v2.cooldict.model;

import com.goo32v2.cooldict.data.models.DictionaryModel;

import java.util.List;

/**
 * Created by aleste on 05.07.16.
 */
public interface DictionaryManagerModelContract {

    List<DictionaryModel> get();

    void save(DictionaryModel model);

    void remove(DictionaryModel model);
}
