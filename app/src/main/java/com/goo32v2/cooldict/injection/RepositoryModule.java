package com.goo32v2.cooldict.injection;

import android.content.Context;
import android.support.annotation.NonNull;

import com.goo32v2.cooldict.data.daos.DictionaryDao;
import com.goo32v2.cooldict.data.daos.WordDao;
import com.goo32v2.cooldict.data.repositories.DictionaryRepository;
import com.goo32v2.cooldict.data.repositories.WordRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created on 03-Jul-16. (c) CoolDict
 */

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    public WordRepository provideWordRepository(@NonNull Context context){
        return WordRepository.getInstance(WordDao.getInstance(context));
    }

    @Provides
    @Singleton
    public DictionaryRepository provideDictionaryRepository(@NonNull Context context){
        return DictionaryRepository.getInstance(DictionaryDao.getInstance(context));
    }
}
