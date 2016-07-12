package com.goo32v2.cooldict.presenter;

import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.view.WordListViewContract;

/**
 * Created on 14-May-16. (c) CoolDict
 */

public interface WordListPresenterContract extends BasePresenter<WordListViewContract> {

    void setDictionaryMenuData();

    void getWords(DictionaryModel dictionary);

    void navigateToDictionaryManagerActivity();

    void navigateToSettingsActivity();

    void navigateToWordManagerActivity();

    void navigateToWordDetailActivity(WordModel word);

    void actionCreateDictionary(String dictionary);
}
