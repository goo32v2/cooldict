package com.goo32v2.cooldict.data;

import android.support.annotation.NonNull;

import com.goo32v2.cooldict.data.daos.WordsDao;

/**
 * Created on 17-May-16. (c) CoolDict
 */

public class WordsRepository {

    private final WordsDao mWordsDao;

    public WordsRepository(@NonNull WordsDao wordsDao){
        this.mWordsDao = wordsDao;
    }


}
