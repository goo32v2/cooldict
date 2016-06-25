package com.goo32v2.cooldict.words;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.goo32v2.cooldict.R;

/**
 * Created on 16-Jun-16. (c) CoolDict
 */

public class WordRecycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView originalWordTV;
    public TextView translatedWordTV;
    private View.OnClickListener listener;

    public WordRecycleViewHolder(View itemView) {
        super(itemView);
        originalWordTV = (TextView) itemView.findViewById(R.id.original_word);
        translatedWordTV = (TextView) itemView.findViewById(R.id.translated_word);
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v);
    }
}
