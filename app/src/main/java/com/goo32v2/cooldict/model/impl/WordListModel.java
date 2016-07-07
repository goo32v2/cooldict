package com.goo32v2.cooldict.model.impl;

import com.goo32v2.cooldict.data.DataSource;
import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.data.repositories.DictionaryRepository;
import com.goo32v2.cooldict.data.repositories.WordRepository;
import com.goo32v2.cooldict.model.WordListModelContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 02-Jul-16. (c) CoolDict
 */

public class WordListModel implements WordListModelContract {

    private DictionaryRepository mDictionaryRepository;
    private WordRepository mWordRepository;

    private List<WordModel> activeWordModelList;
    private List<DictionaryModel> activeDictionaryModelList;

    public WordListModel(WordRepository wordRepository, DictionaryRepository dictionaryRepository) {
        this.mWordRepository = wordRepository;
        this.mDictionaryRepository = dictionaryRepository;
    }

    @Override
    public List<WordModel> getWordListBy(final String dictionary) {
        final DataSource.GetListCallback<WordModel> callback = new DataSource.GetListCallback<WordModel>() {
            @Override
            public void onLoaded(List<WordModel> entries) {
                activeWordModelList = entries;
            }

            @Override
            public void onDataNotAvailable() {
                activeWordModelList = new ArrayList<>();
            }
        };

        // first get dictionary by name, than find all words assign to find id
        if (dictionary != null && !dictionary.equals("")){
            mDictionaryRepository.getDictionaryByName(dictionary, new DataSource.GetListCallback<DictionaryModel>() {
                @Override
                public void onLoaded(List<DictionaryModel> entries) {
                    mWordRepository.getWordsByDictionary(entries.get(0).getId(), callback);
                }

                @Override public void onDataNotAvailable() {}
            });
        } else {
            mWordRepository.getWordsList(callback);
        }

        return activeWordModelList;
    }

    @Override
    public List<DictionaryModel> getDictionaryList() {
        mDictionaryRepository.getDictionaryList(new DataSource.GetListCallback<DictionaryModel>() {
            @Override
            public void onLoaded(List<DictionaryModel> entries) {
                activeDictionaryModelList = entries;
            }

            @Override
            public void onDataNotAvailable() {
                activeDictionaryModelList = new ArrayList<>();
            }
        });

        return activeDictionaryModelList;
    }
}
