package com.goo32v2.cooldict.words.interfaces;

import android.view.View;

import com.goo32v2.cooldict.BaseView;
import com.goo32v2.cooldict.data.models.ModelDTO;
import com.goo32v2.cooldict.data.models.WordModel;

import java.util.List;

/**
 * Created on 14-May-16. (c) CoolDict
 */

public interface WordViewContract extends BaseView<WordPresenterContract> {

    void startAddWordActivity();

    void startSettingsActivity();

    void startWordDetailActivity(WordModel word);

    void showMessage(String msg);

    interface FragmentView {

        void showWords(List<ModelDTO<WordModel, View.OnClickListener>> words);

        void showNoWords();

        boolean isActive();
    }
}
