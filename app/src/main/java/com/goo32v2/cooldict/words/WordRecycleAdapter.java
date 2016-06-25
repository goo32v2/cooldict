package com.goo32v2.cooldict.words;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goo32v2.cooldict.R;
import com.goo32v2.cooldict.data.models.ModelDTO;
import com.goo32v2.cooldict.data.models.WordModel;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created on 16-Jun-16. (c) CoolDict
 */

public class WordRecycleAdapter extends RecyclerView.Adapter<WordRecycleViewHolder> {

    private List<ModelDTO<WordModel, View.OnClickListener>> modelDTOs;

    public WordRecycleAdapter(List<ModelDTO<WordModel, View.OnClickListener>> list) {
        setList(list);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public WordRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_words, parent, false);
        return new WordRecycleViewHolder(root);
    }

    @Override
    public void onBindViewHolder(WordRecycleViewHolder holder, int position) {
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
