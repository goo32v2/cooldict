package com.goo32v2.cooldict.addword;

import android.support.annotation.NonNull;

import com.goo32v2.cooldict.addword.interfaces.AddWordPresenterContract;
import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.data.sources.interfaces.DataSource;
import com.goo32v2.cooldict.data.sources.interfaces.DictDataSource;
import com.goo32v2.cooldict.data.sources.interfaces.WordDataSource;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created on 26-Jun-16. (c) CoolDict
 */

public class AddWordPresenter implements AddWordPresenterContract {

    @NonNull private final WordDataSource mWordRepository;

    @NonNull final DictDataSource mDictionaryRepository;

    @NonNull private final AddWordActivity mAddWordView;

    public AddWordPresenter(@NonNull WordDataSource wordRepository,
                            @NonNull DictDataSource dictionaryRepository,
                            @NonNull AddWordActivity addWordActivity) {
        mAddWordView = checkNotNull(addWordActivity);
        mWordRepository = checkNotNull(wordRepository);
        mDictionaryRepository = checkNotNull(dictionaryRepository);

        mAddWordView.setPresenter(this);
    }


    @Override
    public void getDictionaryNames(@NonNull final DataSource.GetListCallback<String> callback) {
        mDictionaryRepository.getDictionaryList(new DataSource.GetListCallback<DictionaryModel>() {
            @Override
            public void onLoaded(List<DictionaryModel> entries) {
                callback.onLoaded(getAllNames(entries));
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    private List<String> getAllNames(List<DictionaryModel> target){
        List<String> names = new ArrayList<>();
        for (DictionaryModel model : target) {
            names.add(model.getTitle());
        }
        return names;
    }

    @Override
    public void showMessage(String msg) {
        mAddWordView.showMessage(msg);
    }

    @Override
    public void createWord(String originalWord, String translatedWord, final String dictionary) {
        final DictionaryModel[] model = new DictionaryModel[1];
        mDictionaryRepository.getDictionaryByName(dictionary, new DataSource.GetListCallback<DictionaryModel>() {
            @Override
            public void onLoaded(List<DictionaryModel> entries) {
                model[0] = entries.get(0);
            }

            @Override
            public void onDataNotAvailable() {
                model[0] = new DictionaryModel(dictionary);
                mDictionaryRepository.save(model[0]);
            }
        });

        WordModel wordModel = new WordModel(
                originalWord,
                translatedWord,
                model[0].getId());
        mWordRepository.save(wordModel);
        mAddWordView.finishActivity();
    }

    @Override
    public void start() {}
}
