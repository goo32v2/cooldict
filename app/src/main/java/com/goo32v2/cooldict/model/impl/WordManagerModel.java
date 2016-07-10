package com.goo32v2.cooldict.model.impl;

import android.support.annotation.Nullable;

import com.goo32v2.cooldict.data.DataSource;
import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.data.repositories.DictionaryRepository;
import com.goo32v2.cooldict.data.repositories.WordRepository;
import com.goo32v2.cooldict.model.WordManagerModelContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 04-Jul-16. (c) CoolDict
 */

public class WordManagerModel implements WordManagerModelContract {

    private DictionaryRepository mDictionaryRepository;
    private WordRepository mWordRepository;

    private List<String> dictionaryModelList;

    public WordManagerModel(WordRepository mWordRepository, DictionaryRepository mDictionaryRepository) {
        this.mWordRepository = mWordRepository;
        this.mDictionaryRepository = mDictionaryRepository;
    }

    @Override
    public List<String> getDictionaryNames() {
        mDictionaryRepository.getDictionaryList(new DataSource.GetListCallback<DictionaryModel>() {
            @Override
            public void onLoaded(List<DictionaryModel> entries) {
                dictionaryModelList = getNames(entries);
            }

            @Override
            public void onDataNotAvailable() {
                dictionaryModelList = new ArrayList<>();
            }
        });

        return dictionaryModelList;
    }

    private List<String> getNames(List<DictionaryModel> source){
        List<String> names = new ArrayList<>();
        for (DictionaryModel model : source) {
            names.add(model.getTitle());
        }
        return names;
    }

    @Override
    public void create(WordModel model) {
        mWordRepository.save(model);
    }

    @Override
    public void update(WordModel model) {
        mWordRepository.update(model);
    }

    @Override
    @Nullable
    public DictionaryModel getDictionaryByName(final String name) {
        final DictionaryModel[] model = new DictionaryModel[1];
        mDictionaryRepository.getDictionaryByName(name, new DataSource.GetListCallback<DictionaryModel>() {
            @Override
            public void onLoaded(List<DictionaryModel> entries) {
                model[0] = entries.get(0);
            }

            @Override
            public void onDataNotAvailable() {
                model[0] = null;
            }
        });
        return model[0];
    }

    @Override
    public void saveDictionary(DictionaryModel model){
        mDictionaryRepository.save(model);
    }

//    @Override
//    public DictionaryModel getDictionary(String dictionaryID) {
//        final DictionaryModel[] model = new DictionaryModel[1];
//        mDictionaryRepository.getDictionaryById(dictionaryID, new DataSource.GetListCallback<DictionaryModel>() {
//            @Override
//            public void onLoaded(List<DictionaryModel> entries) {
//                model[0] = entries.get(0);
//            }
//
//            @Override
//            public void onDataNotAvailable() {
//                model[0] = null;
//            }
//        });
//        return model[0];
//    }
}
