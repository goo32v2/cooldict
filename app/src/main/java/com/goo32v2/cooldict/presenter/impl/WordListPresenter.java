package com.goo32v2.cooldict.presenter.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.Pair;
import android.view.View;

import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.model.impl.WordListMvpModel;
import com.goo32v2.cooldict.presenter.WordListPresenterContract;
import com.goo32v2.cooldict.utils.Navigator;
import com.goo32v2.cooldict.view.WordListViewContract;
import com.goo32v2.cooldict.view.activities.WordListActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created on 14-May-16. (c) CoolDict
 */

public class WordListPresenter implements WordListPresenterContract {

    private WordListViewContract mView;
    private WordListMvpModel model;

    private Navigator navigator;


    public WordListPresenter(Navigator navigator, WordListMvpModel model) {
        this.navigator = navigator;
        this.model = model;
    }

//    @Override
//    public void start(DictionaryModel dictionary) {
//        setDictionaryMenuData();
//        getWords(dictionary);
//    }

    @Override
    public void onCreate(WordListViewContract view) {
        this.mView = view;
    }

    @Override
    public void onResume(Bundle bundle) {
        setDictionaryMenuData();

        if (!bundle.isEmpty() && bundle.containsKey(WordListActivity.EXTRA_DICTIONARY)) {
            getWords((DictionaryModel) bundle.getSerializable(WordListActivity.EXTRA_DICTIONARY));
        } else {
            getWords(null);
        }
    }

    @Override
    public void onPause() {
        mView.clearDictionaryMenuData();
    }

    @Override
    public void onDestroy() {
        mView = null;
    }

    @Override
    public void setDictionaryMenuData() {
        List<DictionaryModel> dictionaryModels = model.getDictionaryList();

        // create dictionary map
        Map<String, DictionaryModel> dictionaryModelMap = new ArrayMap<>();
        for (DictionaryModel dictionaryModel : dictionaryModels) {
            dictionaryModelMap.put(dictionaryModel.getTitle(), dictionaryModel);
        }

        mView.setDictionaryMenuData(dictionaryModelMap);
    }

    @Override
    public void getWords(DictionaryModel dictionary) {
        List<WordModel> wordModels = model.getWordListBy(dictionary);
        List<Pair<WordModel, View.OnClickListener>> res = new ArrayList<>();

        if (wordModels != null && !wordModels.isEmpty()){
            for (final WordModel word : wordModels) {
                res.add(new Pair<WordModel, View.OnClickListener>(word, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        navigateToWordDetailActivity(word);
                    }
                }));
            }
            mView.showList(res);
        } else {
            mView.showNoWords();
        }
    }

    @Override
    public void actionCreateDictionary(String dictionary) {
        model.createDictionary(dictionary);
    }

    @Override
    public void navigateToSettingsActivity() {
        navigator.navigateToSettingsActivity();
    }

    @Override
    public void navigateToWordManagerActivity() {
//        navigator.navigateToWordManagerActivity(null);
    }

    @Override
    public void navigateToWordDetailActivity(@NonNull WordModel word) {
//        navigator.navigateToWordDetailActivity(word);
    }

    @Override
    public void navigateToDictionaryManagerActivity() {
        navigator.navigateToDictionaryManagerActivity();
    }
}
