package com.goo32v2.cooldict;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created on 03-May-16. (c) CoolDict
 */
public class CoolDictApplication extends Application {

    @NonNull
    public static CoolDictApplication get(@NonNull Context context){
        return (CoolDictApplication) context.getApplicationContext();
    }

    @Override public void onCreate() {
        super.onCreate();
    }
}
