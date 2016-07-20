package com.goo32v2.cooldict;

import android.app.Application;

import com.goo32v2.cooldict.injection.AppComponent;
import com.goo32v2.cooldict.injection.AppModule;
import com.goo32v2.cooldict.injection.DaggerAppComponent;
import com.goo32v2.cooldict.injection.DataModule;
import com.goo32v2.cooldict.injection.UtilsModule;

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

    private AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .utilsModule(new UtilsModule())
                .dataModule(new DataModule())
                .build();
    }
}
