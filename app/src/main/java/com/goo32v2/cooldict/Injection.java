package com.goo32v2.cooldict;

import android.content.Context;
import android.support.annotation.NonNull;

import com.goo32v2.cooldict.data.repositories.DictionaryRepository;
import com.goo32v2.cooldict.data.repositories.WordRepository;
import com.goo32v2.cooldict.data.daos.DictionaryDao;
import com.goo32v2.cooldict.data.daos.WordDao;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created on 17-May-16. (c) CoolDict
 */

public class Injection {

    public static WordRepository provideWordRepository(@NonNull Context context){
        checkNotNull(context);
        return WordRepository.getInstance(WordDao.getInstance(context));
    }

    public static DictionaryRepository provideDictionaryRepository(@NonNull Context context){
        checkNotNull(context);
        return DictionaryRepository.getInstance(DictionaryDao.getInstance(context));
    }
}
