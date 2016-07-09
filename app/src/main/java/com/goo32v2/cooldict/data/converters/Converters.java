package com.goo32v2.cooldict.data.converters;

import android.view.View;

import com.goo32v2.cooldict.data.dtos.Pair;
import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.models.WordModel;

/**
 * Created on 03-Jul-16. (c) CoolDict
 */

public class Converters {

    public static Pair<WordModel, View.OnClickListener> uniteWordWithListener(
            WordModel source, View.OnClickListener listener) {
        return new Pair<>(source, listener);
    }

    public static Pair<DictionaryModel, View.OnClickListener> uniteDictionaryWithListener(
            DictionaryModel source, View.OnClickListener listener) {
        return new Pair<>(source, listener);
    }
}
