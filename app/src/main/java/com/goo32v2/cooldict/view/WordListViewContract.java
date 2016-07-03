package com.goo32v2.cooldict.view;

import android.view.View;

import com.goo32v2.cooldict.data.dtos.ModelDTO;
import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.models.WordModel;

import java.util.List;

/**
 * Created on 14-May-16. (c) CoolDict
 */

public interface WordListViewContract extends BaseView {

    void setMenu(List<DictionaryModel> entries);

    void showMessage(String msg);

    void showList(List<ModelDTO<WordModel, View.OnClickListener>> words);

    void showNoWords();
}
