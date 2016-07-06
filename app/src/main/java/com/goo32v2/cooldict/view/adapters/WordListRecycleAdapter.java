package com.goo32v2.cooldict.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goo32v2.cooldict.R;
import com.goo32v2.cooldict.data.dtos.ModelDTO;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.view.viewholders.WordListRecycleViewHolder;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created on 16-Jun-16. (c) CoolDict
 */

public class WordListRecycleAdapter extends RecyclerView.Adapter<WordListRecycleViewHolder> {

    private List<ModelDTO<WordModel, View.OnClickListener>> modelDTOs;

    public WordListRecycleAdapter(List<ModelDTO<WordModel, View.OnClickListener>> list) {
        setList(list);
    }

    @Override
    public WordListRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_words, parent, false);
        return new WordListRecycleViewHolder(root);
    }

    @Override
    public void onBindViewHolder(WordListRecycleViewHolder holder, int position) {
        ModelDTO<WordModel, View.OnClickListener> model = modelDTOs.get(holder.getAdapterPosition());
        holder.originalWordTV.setText(model.getModel().getOriginalWord());
        holder.translatedWordTV.setText(model.getModel().getTranslatedWord());
        holder.setListener(model.getCallback());
    }

    @Override
    public int getItemCount() {
        return modelDTOs.size();
    }

    public void replaceData(List<ModelDTO<WordModel, View.OnClickListener>> list){
        setList(list);
        notifyDataSetChanged();
    }

    private void setList(List<ModelDTO<WordModel, View.OnClickListener>> list) {
        this.modelDTOs = checkNotNull(list);
    }
}