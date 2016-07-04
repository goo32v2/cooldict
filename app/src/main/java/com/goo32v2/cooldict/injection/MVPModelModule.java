package com.goo32v2.cooldict.injection;

import com.goo32v2.cooldict.data.repositories.DictionaryRepository;
import com.goo32v2.cooldict.data.repositories.WordRepository;
import com.goo32v2.cooldict.model.impl.WordDetailModel;
import com.goo32v2.cooldict.model.impl.WordListModel;
import com.goo32v2.cooldict.model.impl.WordManagerModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created on 03-Jul-16. (c) CoolDict
 */

@Module
public class MVPModelModule {

    @Provides
    public WordListModel provideWordListModel(WordRepository wordRepository, DictionaryRepository dictionaryRepository){
        return new WordListModel(wordRepository, dictionaryRepository);
    }

    @Provides
    public WordDetailModel provideWordDetailModel(WordRepository wordRepository, DictionaryRepository dictionaryRepository){
        return new WordDetailModel(wordRepository, dictionaryRepository);
    }

    @Provides
    public WordManagerModel provideWordManagerModel(WordRepository wordRepository, DictionaryRepository dictionaryRepository){
        return new WordManagerModel(dictionaryRepository, wordRepository);
    }
}
