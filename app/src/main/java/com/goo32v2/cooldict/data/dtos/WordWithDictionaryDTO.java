package com.goo32v2.cooldict.data.dtos;

/**
 * Created on 28-Jun-16. (c) CoolDict
 */

// TODO: 06-Jul-16 create more abstract method and change model name
public class WordWithDictionaryDTO<W, D> {
    private W word;
    private D dictionary;

    public WordWithDictionaryDTO(W model, D dictionary) {
        this.word = model;
        this.dictionary = dictionary;
    }

    public W getWord() {
        return word;
    }

    public void setWord(W word) {
        this.word = word;
    }

    public D getDictionary() {
        return dictionary;
    }

    public void setDictionary(D dictionary) {
        this.dictionary = dictionary;
    }
}

