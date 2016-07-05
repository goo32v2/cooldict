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

    public WordManagerModel(DictionaryRepository mDictionaryRepository, WordRepository mWordRepository) {
        this.mDictionaryRepository = mDictionaryRepository;
        this.mWordRepository = mWordRepository;
    }

    @Override
    public List<String> getDictionaryNames() {
        mDictionaryRepository.getDictionaryList(new DataSource.GetListCallback<DictionaryModel>() {
            @Override
            public void onLoaded(List<DictionaryModel> entries) {
                dictionaryModelList = getNames(entries);
            }

            @Override
            public void onDataNotAvailable() {}
        });

        return dictionaryModelList;
    }

    private List<String> getNames(List<DictionaryModel> target){
        List<String> names = new ArrayList<>();
        for (DictionaryModel model : target) {
            names.add(model.getTitle());
        }
        return names;
    }

    @Override
    public void create(String originalWord, String translatedWord, final String dictionary) {
        DictionaryModel dictionaryModel = getDictionaryByName(dictionary);
        WordModel wordModel = new WordModel(originalWord, translatedWord, dictionaryModel.getId());

        mWordRepository.save(wordModel);
    }

    @Override
    public void update(String id, String originalText, String translatedText, final String dictionary) {
        DictionaryModel dictionaryModel = getDictionaryByName(dictionary);
        WordModel wordModel = new WordModel(id, originalText, translatedText, dictionaryModel.getId());

        mWordRepository.update(id, wordModel);
    }

    @Override
    public DictionaryModel getDictionaryByName(final String name) {
        final DictionaryModel[] model = new DictionaryModel[1];
        mDictionaryRepository.getDictionaryByName(name, new DataSource.GetListCallback<DictionaryModel>() {
            @Override
            public void onLoaded(List<DictionaryModel> entries) {
                model[0] = entries.get(0);
            }

            @Override
            public void onDataNotAvailable() {
                model[0] = new DictionaryModel(name);
                saveDictionary(model[0]);
            }
        });
        return model[0];
    }

    @Override
    public void saveDictionary(DictionaryModel model){
        mDictionaryRepository.save(model);
    }

    @Override
    public DictionaryModel getDictionary(String dictionaryID) {
        final DictionaryModel[] model = new DictionaryModel[1];
        mDictionaryRepository.getDictionary(dictionaryID, new DataSource.GetListCallback<DictionaryModel>() {
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
}
