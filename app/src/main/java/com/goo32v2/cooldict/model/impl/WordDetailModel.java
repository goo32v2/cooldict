package com.goo32v2.cooldict.model.impl;

import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.data.repositories.WordRepository;
import com.goo32v2.cooldict.model.WordDetailModelContract;

/**
 * Created on 03-Jul-16. (c) CoolDict
 */

public class WordDetailModel implements WordDetailModelContract {

    private WordRepository mWordRepository;

    public WordDetailModel(WordRepository wordRepository) {
        this.mWordRepository = wordRepository;
    }

    @Override
    public void actionDeleteWord(WordModel word) {
        mWordRepository.remove(word);
    }
}
