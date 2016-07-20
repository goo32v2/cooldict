package com.goo32v2.cooldict.injection;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created on 02-Jul-16. (c) CoolDict
 */

@Component(modules = {
        AppModule.class,
        DataModule.class,
        UtilsModule.class
})
@Singleton
public interface AppComponent {

}
