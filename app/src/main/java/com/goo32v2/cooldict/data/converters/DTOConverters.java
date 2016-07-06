package com.goo32v2.cooldict.data.converters;

import android.view.View;

import com.goo32v2.cooldict.data.dtos.ModelDTO;
import com.goo32v2.cooldict.data.dtos.WordWithDictionaryDTO;
import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.models.WordModel;

/**
 * Created on 03-Jul-16. (c) CoolDict
 */

// TODO: 03-Jul-16 more abstract!!!
public class DTOConverters {

    public static ModelDTO<WordModel, View.OnClickListener> convertWordToDTO(
            WordModel source, View.OnClickListener listener) {
        return new ModelDTO<>(source, listener);
    }

    public static WordWithDictionaryDTO<WordModel, DictionaryModel> convertModelToModelDTO(WordModel word, DictionaryModel dictionary){
        return new WordWithDictionaryDTO<>(word, dictionary);
    }

    public static ModelDTO<DictionaryModel, View.OnClickListener> convertDictionaryToDTO(
            DictionaryModel source, View.OnClickListener listener) {
        return new ModelDTO<>(source, listener);
    }
}
