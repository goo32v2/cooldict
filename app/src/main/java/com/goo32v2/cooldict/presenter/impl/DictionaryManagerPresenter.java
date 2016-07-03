package com.goo32v2.cooldict.presenter.impl;

/**
 * Created on 29-Jun-16. (c) CoolDict
 */

public class DictionaryManagerPresenter{
//public class DictionaryManagerPresenter implements DictionaryManagerPresenterContract{

//    private DictionaryManagerViewContract mView;
//    private DictionaryRepository mDictionaryRepository;
//    private WordRepository mWordRepository;
//
//    public DictionaryManagerPresenter(@NonNull DictionaryRepository dictionaryRepository,
//                                      @NonNull WordRepository wordRepository,
//                                      @NonNull DictionaryManagerViewContract view){
//        this.mDictionaryRepository = dictionaryRepository;
//        this.mWordRepository = wordRepository;
//        this.mView = view;
//
//        mView.setPresenter(this);
//    }
//
//    @Override
//    public void get(@NonNull DataSource.GetListCallback<DictionaryModel> callback) {
//        mDictionaryRepository.getDictionaryList(callback);
//    }
//
//    @Override
//    public void save(DictionaryModel model) {
//        mDictionaryRepository.save(model);
//    }
//
//    @Override
//    public void remove(final DictionaryModel model) {
//        mWordRepository.getWordsByDictionary(model.getId(), new DataSource.GetListCallback<WordModel>() {
//            @Override
//            public void onLoaded(List<WordModel> entries) {
//                for (WordModel entry : entries) {
//                    mWordRepository.remove(entry);
//                }
//            }
//
//            @Override
//            public void onDataNotAvailable() {
//            }
//        });
//        mDictionaryRepository.remove(model);
//    }
//
//    @Override
//    public void start() {
//
//    }
}
