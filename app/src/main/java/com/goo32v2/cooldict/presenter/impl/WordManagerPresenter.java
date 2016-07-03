package com.goo32v2.cooldict.presenter.impl;

/**
 * Created on 26-Jun-16. (c) CoolDict
 */

public class WordManagerPresenter {
//public class WordManagerPresenter implements WordManagerPresenterContract {

//    @NonNull private final WordDataSource mWordRepository;
//
//    @NonNull final DictDataSource mDictionaryRepository;
//
//    @NonNull private final WordManagerActivity mView;
//
//    public WordManagerPresenter(@NonNull WordDataSource wordRepository,
//                                @NonNull DictDataSource dictionaryRepository,
//                                @NonNull WordManagerActivity addWordActivity) {
//        mView = checkNotNull(addWordActivity);
//        mWordRepository = checkNotNull(wordRepository);
//        mDictionaryRepository = checkNotNull(dictionaryRepository);
//
//        mView.setPresenter(this);
//    }
//
//
//    @Override
//    public void getDictionaryNames(@NonNull final DataSource.GetListCallback<String> callback) {
//        mDictionaryRepository.getDictionaryList(new DataSource.GetListCallback<DictionaryModel>() {
//            @Override
//            public void onLoaded(List<DictionaryModel> entries) {
//                callback.onLoaded(getAllNames(entries));
//            }
//
//            @Override
//            public void onDataNotAvailable() {
//                callback.onDataNotAvailable();
//            }
//        });
//    }
//
//    private List<String> getAllNames(List<DictionaryModel> target){
//        List<String> names = new ArrayList<>();
//        for (DictionaryModel model : target) {
//            names.add(model.getTitle());
//        }
//        return names;
//    }
//
//    @Override
//    public void showMessage(String msg) {
//        mView.showMessage(msg);
//    }
//
//    @Override
//    public void create(String originalWord, String translatedWord, final String dictionary) {
//        final DictionaryModel[] model = new DictionaryModel[1];
//        mDictionaryRepository.getDictionaryByName(dictionary, new DataSource.GetListCallback<DictionaryModel>() {
//            @Override
//            public void onLoaded(List<DictionaryModel> entries) {
//                model[0] = entries.get(0);
//            }
//
//            @Override
//            public void onDataNotAvailable() {
//                model[0] = new DictionaryModel(dictionary);
//                mDictionaryRepository.save(model[0]);
//            }
//        });
//
//        WordModel wordModel = new WordModel(
//                originalWord,
//                translatedWord,
//                model[0].getId());
//        mWordRepository.save(wordModel);
//        mView.finishActivity();
//    }
//
//    @Override
//    public void update(String id, String originalText, String translatedText, final String dictionary) {
//        final DictionaryModel[] model = new DictionaryModel[1];
//        mDictionaryRepository.getDictionaryByName(dictionary, new DataSource.GetListCallback<DictionaryModel>() {
//            @Override
//            public void onLoaded(List<DictionaryModel> entries) {
//                model[0] = entries.get(0);
//            }
//
//            @Override
//            public void onDataNotAvailable() {
//                model[0] = new DictionaryModel(dictionary);
//                mDictionaryRepository.save(model[0]);
//            }
//        });
//        WordModel wordModel = new WordModel(id, originalText, translatedText, model[0].getId());
//        mWordRepository.update(id, wordModel);
//        mView.finishActivity();
//    }
//
//    @Override
//    public void populate(WordModel wordModel) {
//        final DictionaryModel[] dictionary = new DictionaryModel[1];
//        mDictionaryRepository.getDictionary(wordModel.getDictionaryID(),
//                new DataSource.GetListCallback<DictionaryModel>() {
//            @Override
//            public void onLoaded(List<DictionaryModel> entries) {
//                dictionary[0] = entries.get(0);
//            }
//
//            @Override
//            public void onDataNotAvailable() {
//                dictionary[0] = new DictionaryModel(Constants.DEFAULT_DICTIONARY_NAME);
//            }
//        });
//
//        mView.populateWord(
//                wordModel.getId(),
//                wordModel.getOriginalWord(),
//                wordModel.getTranslatedWord(),
//                dictionary[0].getTitle()
//        );
//    }
//
//    @Override
//    public void start() {}
}
