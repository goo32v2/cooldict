package com.goo32v2.cooldict.injection;

import android.content.Context;
import android.support.annotation.NonNull;

import com.goo32v2.cooldict.data.repositories.DictionaryRepository;
import com.goo32v2.cooldict.data.repositories.WordRepository;
import com.goo32v2.cooldict.data.daos.DictionaryDao;
import com.goo32v2.cooldict.data.daos.WordDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created on 17-May-16. (c) CoolDict
 */

@Module
public class AppModule {

    private Context appContext;

    public AppModule(@NonNull Context context){
        appContext = context;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return appContext;
    }

    @Provides
    @Singleton
    public WordRepository provideWordRepository(@NonNull Context context){
        checkNotNull(context);
        return WordRepository.getInstance(WordDao.getInstance(context));
    }

    @Provides
    @Singleton
    public DictionaryRepository provideDictionaryRepository(@NonNull Context context){
        checkNotNull(context);
        return DictionaryRepository.getInstance(DictionaryDao.getInstance(context));
    }
}
