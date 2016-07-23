package com.goo32v2.cooldict.injection;

import com.goo32v2.cooldict.view.activities.WordsListActivity;

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
    void inject(WordsListActivity wordsListActivity);

}
