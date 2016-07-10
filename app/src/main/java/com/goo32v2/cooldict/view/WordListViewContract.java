package com.goo32v2.cooldict.view;

import android.view.View;

import com.goo32v2.cooldict.data.dtos.Pair;
import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.models.WordModel;

import java.util.List;
import java.util.Map;

/**
 * Created on 14-May-16. (c) CoolDict
 */

public interface WordListViewContract extends BaseView {

    void setMenu(Map<String, DictionaryModel> entries);

    void showMessage(String msg);

    void showList(List<Pair<WordModel, View.OnClickListener>> words);

    void showNoWords();
}
