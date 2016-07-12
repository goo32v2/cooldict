package com.goo32v2.cooldict.view;

import android.support.v4.util.Pair;
import android.view.View;

import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.models.WordModel;

import java.util.List;
import java.util.Map;

/**
 * Created on 14-May-16. (c) CoolDict
 */

public interface WordListViewContract extends BaseView {

    void showList(List<Pair<WordModel, View.OnClickListener>> words);

    void showNoWords();

    void setDictionaryMenuData(Map<String, DictionaryModel> entries);

    void clearDictionaryMenuData();
}
