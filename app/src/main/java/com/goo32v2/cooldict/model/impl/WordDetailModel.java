package com.goo32v2.cooldict.model.impl;

import com.goo32v2.cooldict.data.DataSource;
import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.data.repositories.DictionaryRepository;
import com.goo32v2.cooldict.data.repositories.WordRepository;
import com.goo32v2.cooldict.model.WordDetailModelContract;

import java.util.List;

/**
 * Created on 03-Jul-16. (c) CoolDict
 */

public class WordDetailModel implements WordDetailModelContract {

    private WordRepository mWordRepository;
    private DictionaryRepository mDictionaryRepository;

    public WordDetailModel(WordRepository wordRepository, DictionaryRepository dictionaryRepository) {
        this.mWordRepository = wordRepository;
        this.mDictionaryRepository = dictionaryRepository;
    }

    @Override
    public void actionDeleteWord(WordModel word) {
        mWordRepository.remove(word);
    }

    @Override
    public DictionaryModel getDictionary(String id) {
        final DictionaryModel[] res = new DictionaryModel[1];
        mDictionaryRepository.getDictionary(id, new DataSource.GetListCallback<DictionaryModel>() {
            @Override
            public void onLoaded(List<DictionaryModel> entries) {
                res[0] = entries.get(0);
            }

            @Override
            public void onDataNotAvailable() {}
        });

        return res[0];
    }
}
