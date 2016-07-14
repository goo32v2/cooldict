package com.goo32v2.cooldict.presenter.impl;

/**
 * Created on 15-Jun-16. (c) CoolDict
 */

//public class WordDetailPresenter {
//public class WordDetailPresenter implements WordDetailPresenterContract {
//
//    private WordDetailViewContract mView;
//    private WordDetailModel mModel;
//    private Navigator mNavigator;
//
//
//    public WordDetailPresenter(Navigator navigator, WordDetailModel model){
//        this.mModel = model;
//        this.mNavigator = navigator;
//    }
//
//    @Override
//    public void navigateToWordManagerActivity(WordModel word) {
//        mNavigator.navigateToWordManagerActivity(word);
//    }
//
//    @Override
//    public void actionDeleteWord(WordModel word) {
//        mModel.actionDeleteWord(word);
//        mView.finishActivity();
//    }
//
//    @Override
//    public void start() {
//        // after implementation of wordModel changes, it can be removed
////        DictionaryModel dictionary = mModel.getDictionary(word.getDictionaryId());
////        mView.populate(Converters.convertModelToModelDTO(word, dictionary));
//        WordModel word = mView.getExtraWord();
//        mView.populate(word);
//    }
//
//    @Override
//    public void onCreate(WordDetailViewContract view) {
//        mView = view;
//    }
//}
