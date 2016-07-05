package com.goo32v2.cooldict.view;

import android.support.v7.app.AlertDialog;
import android.view.View;

import com.goo32v2.cooldict.data.dtos.ModelDTO;
import com.goo32v2.cooldict.data.models.DictionaryModel;

import java.util.List;

/**
 * Created on 29-Jun-16. (c) CoolDict
 */

public interface DictionaryManagerViewContract extends BaseView {

    AlertDialog getConfirmationDialog(final DictionaryModel model);

    void showDictionaries(List<ModelDTO<DictionaryModel, View.OnClickListener>> list);

}
