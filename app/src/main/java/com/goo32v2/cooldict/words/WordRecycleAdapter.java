package com.goo32v2.cooldict.words;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goo32v2.cooldict.R;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.words.interfaces.WordPresenterContract;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created on 16-Jun-16. (c) CoolDict
 */

public class WordRecycleAdapter extends RecyclerView.Adapter<RecycleViewHolder> {

    private List<WordModel> mWords;
    private WordPresenterContract mPresenter;

    WordRecycleAdapter(List<WordModel> w, WordPresenterContract presenter){
        setList(w);
        mPresenter = presenter;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_item, parent, false);
        RecycleViewHolder rvh = new RecycleViewHolder(root, mPresenter);
        root.setOnClickListener(rvh);
        return rvh;
    }

    @Override
    public void onBindViewHolder(RecycleViewHolder holder, int position) {
        holder.originalWordTV.setText(mWords.get(position).getOriginalWord());
        holder.translatedWordTV.setText(mWords.get(position).getTranslatedWord());
        holder.setWordModel(mWords.get(position));
    }

    @Override
    public int getItemCount() {
        return mWords.size();
    }

    public void replaceData(List<WordModel> words){
        setList(words);
        notifyDataSetChanged();
    }

    public void setList(List<WordModel> list) {
        this.mWords = checkNotNull(list);
    }
}
