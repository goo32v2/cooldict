package com.goo32v2.cooldict.injection;

import com.goo32v2.cooldict.model.impl.DictionaryManagerModel;
import com.goo32v2.cooldict.model.impl.WordDetailModel;
import com.goo32v2.cooldict.model.impl.WordListModel;
import com.goo32v2.cooldict.model.impl.WordManagerModel;
import com.goo32v2.cooldict.presenter.impl.DictionaryManagerPresenter;
import com.goo32v2.cooldict.presenter.impl.WordDetailPresenter;
import com.goo32v2.cooldict.presenter.impl.WordListPresenter;
import com.goo32v2.cooldict.presenter.impl.WordManagerPresenter;
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

    @Provides
    @Singleton
    public WordManagerPresenter provideWordManagerPresenter(WordManagerModel model){
        return new WordManagerPresenter(model);
    }

    @Provides
    @Singleton
    public DictionaryManagerPresenter provideDictionaryManagerPresenter(DictionaryManagerModel model){
        return new DictionaryManagerPresenter(model);
    }
}
