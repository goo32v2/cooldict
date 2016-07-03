package com.goo32v2.cooldict.presenter.impl;

import android.support.annotation.NonNull;
import android.view.View;

import com.goo32v2.cooldict.data.converters.DTOConverters;
import com.goo32v2.cooldict.data.dtos.ModelDTO;
import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.model.impl.WordListModel;
import com.goo32v2.cooldict.presenter.WordListPresenterContract;
import com.goo32v2.cooldict.utils.Navigator;
import com.goo32v2.cooldict.view.WordListViewContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 14-May-16. (c) CoolDict
 */

public class WordListPresenter implements WordListPresenterContract {

    private WordListViewContract mView;
    private WordListModel model;

    private Navigator navigator;
//    @Inject protected Navigator navigator;


    public WordListPresenter(Navigator navigator, WordListModel model) {
        this.navigator = navigator;
        this.model = model;

        // TODO: 03-Jul-16 maybe inject it?
//        model = new WordListModel();
    }

    @Override
    public void start(String dictionary) {
        getDictionaries();
        getWordsBy(dictionary);
    }

    @Override
    public void getDictionaries() {
        List<DictionaryModel> dictionaryModels = model.getDictionaryList();
        mView.setMenu(dictionaryModels);
    }

    @Override
    public void getWordsBy(String dictionary) {
        List<WordModel> wordModels = model.getWordListBy(dictionary);
        List<ModelDTO<WordModel, View.OnClickListener>> res = new ArrayList<>();

        if (wordModels != null && !wordModels.isEmpty()){
            for (final WordModel word : wordModels) {
                res.add(DTOConverters.convertWordToDTO(word, new View.OnClickListener() {
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
    public void navigateToSettingsActivity() {
        navigator.navigateToSettingsActivity();
    }

    @Override
    public void navigateToWordManagerActivity() {
        navigator.navigateToWordManagerActivity(null);
    }

    @Override
    public void navigateToWordDetailActivity(@NonNull WordModel word) {
        navigator.navigateToWordDetailActivity(word);
    }

    @Override
    public void navigateToDictionaryManagerActivity() {
        navigator.navigateToDictionaryManagerActivity();
    }

    @Override
    public void showMessage(String msg) {
        mView.showMessage(msg);
    }

    @Override
    public void start() {

    }

    @Override
    public void setView(WordListViewContract view) {
        mView = view;
    }
}
