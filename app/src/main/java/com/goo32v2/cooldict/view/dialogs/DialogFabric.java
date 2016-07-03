package com.goo32v2.cooldict.view.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created on 03-Jul-16. (c) CoolDict
 */

public class DialogFabric {

    public static AlertDialog getDeleteConfirnationDialog(Context context,
                                                          String title,
                                                          String message,
                                                          String positiveButtonMsg,
                                                          String negativeButtonMsg,
                                                          DialogInterface.OnClickListener positiveListener,
                                                          DialogInterface.OnClickListener negativeListener) {
        return new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtonMsg, positiveListener)
                .setNegativeButton(negativeButtonMsg, negativeListener)
                .create();
    }
}
