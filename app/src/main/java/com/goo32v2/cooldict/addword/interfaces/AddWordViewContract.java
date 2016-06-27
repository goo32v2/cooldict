package com.goo32v2.cooldict.addword.interfaces;

import com.goo32v2.cooldict.BaseView;

/**
 * Created on 26-Jun-16. (c) CoolDict
 */

public interface AddWordViewContract extends BaseView<AddWordPresenterContract> {

    void showMessage(String msg);

    void finishActivity();

}
