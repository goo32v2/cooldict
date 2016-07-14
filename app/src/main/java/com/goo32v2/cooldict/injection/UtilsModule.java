package com.goo32v2.cooldict.injection;

import android.content.Context;

import com.goo32v2.cooldict.utils.Navigator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created on 03-Jul-16. (c) CoolDict
 */

@Module
public class UtilsModule {

    @Provides
    @Singleton
    Navigator provideNavigator(Context context){
        return new Navigator(context);
    }
}
