package com.goo32v2.cooldict.injection;

import android.content.Context;
import android.support.annotation.NonNull;

import com.goo32v2.cooldict.data.DictionariesRepository;
import com.goo32v2.cooldict.data.WordsRepository;
import com.goo32v2.cooldict.data.daos.DictionariesDao;
import com.goo32v2.cooldict.data.daos.WordsDao;
import com.goo32v2.cooldict.data.database.DatabaseHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created on 03-Jul-16. (c) CoolDict
 */

@Module
public class DataModule {

    @Provides
    @Singleton
    DatabaseHelper provideDatabaseHelper(@NonNull Context context){
        return new DatabaseHelper(context);
    }

    @Provides
    @Singleton
    WordsDao provideWordsDao(@NonNull DatabaseHelper helper){
        return new WordsDao(helper);
    }

    @Provides
    @Singleton
    DictionariesDao provideDictionariesDao(@NonNull DatabaseHelper helper){
        return new DictionariesDao(helper);
    }

    @Provides
    @Singleton
    WordsRepository provideWordRepository(@NonNull WordsDao dao){
        return new WordsRepository(dao);
    }

    @Provides
    @Singleton
    DictionariesRepository provideDictionaryRepository(@NonNull DictionariesDao dao){
        return new DictionariesRepository(dao);
    }
}
