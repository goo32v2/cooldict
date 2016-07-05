package com.goo32v2.cooldict.model.impl;

import com.goo32v2.cooldict.data.DataSource;
import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.data.repositories.DictionaryRepository;
import com.goo32v2.cooldict.data.repositories.WordRepository;
import com.goo32v2.cooldict.model.DictionaryManagerModelContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aleste on 05.07.16.
 */

public class DictionaryManagerModel implements DictionaryManagerModelContract {

    private WordRepository mWordRepository;
    private DictionaryRepository mDictionaryRepository;

    private List<DictionaryModel> actualDictionaryList;

    public DictionaryManagerModel(WordRepository mWordRepository, DictionaryRepository mDictionaryRepository) {
        this.mWordRepository = mWordRepository;
        this.mDictionaryRepository = mDictionaryRepository;
    }

    @Override
    public List<DictionaryModel> get() {
        mDictionaryRepository.getDictionaryList(new DataSource.GetListCallback<DictionaryModel>() {
            @Override
            public void onLoaded(List<DictionaryModel> entries) {
                actualDictionaryList = entries;
            }

            @Override
            public void onDataNotAvailable() {
                actualDictionaryList = new ArrayList<>();
            }
        });
        return actualDictionaryList;
    }

    @Override
    public void save(DictionaryModel model) {
        mDictionaryRepository.save(model);
    }

    @Override
    public void remove(DictionaryModel model) {
        mWordRepository.getWordsByDictionary(model.getId(), new DataSource.GetListCallback<WordModel>() {
            @Override
            public void onLoaded(List<WordModel> entries) {
                for (WordModel entry : entries) {
                    mWordRepository.remove(entry);
                }
            }

            @Override
            public void onDataNotAvailable() {}
        });

        mDictionaryRepository.remove(model);
    }
}
