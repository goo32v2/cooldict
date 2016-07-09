package com.goo32v2.cooldict.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goo32v2.cooldict.R;
import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.dtos.Pair;
import com.goo32v2.cooldict.view.viewholders.DictionaryManagerRecycleViewHolder;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created on 25-Jun-16. (c) CoolDict
 */

public class DictionaryManagerRecycleAdapter extends RecyclerView.Adapter<DictionaryManagerRecycleViewHolder> {

    private List<Pair<DictionaryModel, View.OnClickListener>> pairs;

    public DictionaryManagerRecycleAdapter(List<Pair<DictionaryModel, View.OnClickListener>> list) {
        setList(list);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public DictionaryManagerRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dictionary_manager, parent, false);
        return new DictionaryManagerRecycleViewHolder(root);
    }



    @Override
    public void onBindViewHolder(DictionaryManagerRecycleViewHolder holder, int position) {
        Pair<DictionaryModel, View.OnClickListener> model = pairs.get(holder.getAdapterPosition());
        holder.setListener(model.getElement1());
        holder.textView.setText(model.getElement0().getTitle());
    }

    @Override
    public int getItemCount() {
        return pairs.size();
    }

    public void replaceData(List<Pair<DictionaryModel, View.OnClickListener>> list){
        setList(list);
        notifyDataSetChanged();
    }

    private void setList(List<Pair<DictionaryModel, View.OnClickListener>> list) {
        this.pairs = checkNotNull(list);
    }
}
