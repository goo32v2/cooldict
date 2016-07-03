package com.goo32v2.cooldict.injection;

import com.goo32v2.cooldict.model.impl.WordDetailModel;
import com.goo32v2.cooldict.model.impl.WordListModel;
import com.goo32v2.cooldict.presenter.impl.WordDetailPresenter;
import com.goo32v2.cooldict.presenter.impl.WordListPresenter;
import com.goo32v2.cooldict.utils.Navigator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created on 03-Jul-16. (c) CoolDict
 */

@Module
public class PresenterModule {


    @Provides
    @Singleton
    public WordListPresenter provideWordListPresenter(Navigator navigator, WordListModel model){
        return new WordListPresenter(navigator, model);
    }

    @Provides
    @Singleton
    public WordDetailPresenter provideWordDetailPresenter(Navigator navigator, WordDetailModel model){
        return new WordDetailPresenter(navigator, model);
    }
}
