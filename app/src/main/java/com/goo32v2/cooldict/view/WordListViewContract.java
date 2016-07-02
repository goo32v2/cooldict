package com.goo32v2.cooldict.view;

import android.view.View;

import com.goo32v2.cooldict.data.dtos.ModelDTO;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.presenter.WordPresenterContract;

import java.util.List;

/**
 * Created on 14-May-16. (c) CoolDict
 */

public interface WordListViewContract extends BaseView<WordPresenterContract> {

    void startAddWordActivity();

    void startSettingsActivity();

    void startWordDetailActivity(WordModel word);

    void startDictionaryManagerActivity();

    void showMessage(String msg);

    interface FragmentView {

        void showWords(List<ModelDTO<WordModel, View.OnClickListener>> words);

        void showNoWords();

        boolean isActive();
    }
}
