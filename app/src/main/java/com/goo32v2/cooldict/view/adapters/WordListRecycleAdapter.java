package com.goo32v2.cooldict.view.adapters;

import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goo32v2.cooldict.R;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.view.viewholders.WordListRecycleViewHolder;

import java.util.List;

/**
 * Created on 16-Jun-16. (c) CoolDict
 */

public class WordListRecycleAdapter extends RecyclerView.Adapter<WordListRecycleViewHolder> {

    private List<Pair<WordModel, View.OnClickListener>> models;

    public WordListRecycleAdapter(List<Pair<WordModel, View.OnClickListener>> list) {
        setList(list);
    }

    @Override
    public WordListRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_words, parent, false);
        return new WordListRecycleViewHolder(root);
    }

    @Override
    public void onBindViewHolder(WordListRecycleViewHolder holder, int position) {
        WordModel model = models.get(holder.getAdapterPosition()).first;
        View.OnClickListener listener = models.get(holder.getAdapterPosition()).second;
        holder.originalWordTV.setText(model.getOriginalWord());
        holder.translatedWordTV.setText(model.getTranslatedWord());
        holder.setListener(listener);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public void replaceData(List<Pair<WordModel, View.OnClickListener>> list){
        setList(list);
        notifyDataSetChanged();
    }

    private void setList(List<Pair<WordModel, View.OnClickListener>> list) {
        this.models = list;
    }
}
