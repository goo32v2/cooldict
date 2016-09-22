package com.goo32v2.cooldict.view;

import android.content.Context;

/**
 * Created on 30-Jul-16. (c) CoolDict
 */

interface BaseViewModelContract {

    interface BaseView {

        Context getContext();
    }

    interface BaseViewModel {

        void destroy();
    }

}
