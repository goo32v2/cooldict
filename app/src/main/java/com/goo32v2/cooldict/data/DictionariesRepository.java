package com.goo32v2.cooldict.data;

import android.support.annotation.NonNull;

import com.goo32v2.cooldict.data.daos.DictionariesDao;

/**
 * Created on 14-May-16. (c) CoolDict
 */


public class DictionariesRepository {

    private final DictionariesDao mDictionariesDao;

    public DictionariesRepository(@NonNull DictionariesDao dictionariesDao){
        this.mDictionariesDao = dictionariesDao;
    }

}
