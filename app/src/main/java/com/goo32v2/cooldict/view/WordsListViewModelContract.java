package com.goo32v2.cooldict.view;

import com.goo32v2.cooldict.models.DictionaryModel;
import com.goo32v2.cooldict.models.WordModel;

import java.util.List;

/**
 * Created on 30-Jul-16. (c) CoolDict
 */

public interface WordsListViewModelContract {

    interface View extends BaseViewModelContract.BaseView {
        void loadWords(List<WordModel> words);

        void loadDictionaries(List<DictionaryModel> dictionaries);
    }

    interface ViewModel extends BaseViewModelContract.BaseViewModel {

    }
}
