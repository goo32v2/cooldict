package com.goo32v2.cooldict.view.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.goo32v2.cooldict.R;

/**
 * Created on 25-Jun-16. (c) CoolDict
 */

public class DictionaryManagerRecycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView textView;
    private View.OnClickListener listener;

    public DictionaryManagerRecycleViewHolder(View itemView) {
        super(itemView);
        this.textView = (TextView) itemView.findViewById(R.id.dictionary_name);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v);
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }
}
