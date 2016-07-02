package com.goo32v2.cooldict;

import android.app.Application;

import com.goo32v2.cooldict.injection.AppComponent;
import com.goo32v2.cooldict.injection.AppModule;
import com.goo32v2.cooldict.injection.DaggerAppComponent;

/**
 * Created on 02-Jul-16. (c) CoolDict
 */

public class CoolDictApp extends Application {

    private static AppComponent component;

    public static AppComponent getComponent(){
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }
}
