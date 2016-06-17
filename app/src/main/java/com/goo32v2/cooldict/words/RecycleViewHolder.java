package com.goo32v2.cooldict.words;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.goo32v2.cooldict.R;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.words.interfaces.WordPresenterContract;

/**
 * Created on 16-Jun-16. (c) CoolDict
 */

public class RecycleViewHolder extends RecyclerView.ViewHolder {

    public TextView originalWordTV;
    public TextView translatedWordTV;

    public RecycleViewHolder(View itemView) {
        super(itemView);
        originalWordTV = (TextView) itemView.findViewById(R.id.original_word);
        translatedWordTV = (TextView) itemView.findViewById(R.id.translated_word);
    }

}
