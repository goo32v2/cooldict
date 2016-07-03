package com.goo32v2.cooldict.presenter;

import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.view.WordListViewContract;

/**
 * Created on 14-May-16. (c) CoolDict
 */

public interface WordListPresenterContract extends BasePresenter<WordListViewContract> {

//    void getWords(@NonNull DataSource.GetListCallback<WordModel> callback);
//
    void start(String dictionary);

    void getDictionaries();

    void getWordsBy(String dictionary);

    void navigateToDictionaryManagerActivity();

    void navigateToSettingsActivity();

    void navigateToWordManagerActivity();

    void navigateToWordDetailActivity(WordModel word);

    void showMessage(String msg);
}
