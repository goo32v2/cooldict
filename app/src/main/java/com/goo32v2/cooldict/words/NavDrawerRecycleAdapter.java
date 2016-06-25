package com.goo32v2.cooldict.words;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goo32v2.cooldict.R;
import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.models.ModelDTO;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created on 25-Jun-16. (c) CoolDict
 */

public class NavDrawerRecycleAdapter extends RecyclerView.Adapter<NavDrawerRecycleViewHolder> {

    private List<ModelDTO<DictionaryModel, View.OnClickListener>> modelDTOs;

    public NavDrawerRecycleAdapter(List<ModelDTO<DictionaryModel, View.OnClickListener>> list) {
        setList(list);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public NavDrawerRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_navigation_drawer, parent, false);
        return new NavDrawerRecycleViewHolder(root);
    }



    @Override
    public void onBindViewHolder(NavDrawerRecycleViewHolder holder, int position) {
        ModelDTO<DictionaryModel, View.OnClickListener> model = modelDTOs.get(holder.getAdapterPosition());
        holder.setListener(model.getCallback());
        holder.textView.setText(model.getModel().getTitle());
    }

    @Override
    public int getItemCount() {
        return modelDTOs.size();
    }

    public void replaceData(List<ModelDTO<DictionaryModel, View.OnClickListener>> list){
        setList(list);
        notifyDataSetChanged();
    }

    private void setList(List<ModelDTO<DictionaryModel, View.OnClickListener>> list) {
        this.modelDTOs = checkNotNull(list);
    }
}
