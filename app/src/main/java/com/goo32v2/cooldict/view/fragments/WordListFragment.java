package com.goo32v2.cooldict.view.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.goo32v2.cooldict.R;
import com.goo32v2.cooldict.data.dtos.Pair;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.view.adapters.WordListRecycleAdapter;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 14-May-16. (c) CoolDict
 */

public class WordListFragment extends Fragment {

    private WordListRecycleAdapter mWordAdapter;
    private LinearLayoutManager llm;
    @BindView(R.id.wordsLL) RecyclerView mWordRecycleView;
    @BindView(R.id.noWords) View mNoWordsView;
    @BindView(R.id.noWordsText) TextView mNoWordsTextView;

    // must be empty
    public WordListFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_words, container, false);
        ButterKnife.bind(this, root);

        setHasOptionsMenu(true);
        showNoWords();
        return root;
    }

    public void showWords(List<WordModel> words) {
        mNoWordsView.setVisibility(View.GONE);
        mWordRecycleView.setVisibility(View.VISIBLE);

        // if null == first call
        if (llm == null || mWordAdapter == null){
            setupRecycleView(words);
        } else {
            update(words);
        }
    }

    public void showNoWords() {
        mWordRecycleView.setVisibility(View.GONE);
        mNoWordsView.setVisibility(View.VISIBLE);

        mNoWordsTextView.setText(getString(R.string.no_word_text));
    }

    private void setupRecycleView(List<WordModel> words){
        llm = new LinearLayoutManager(getActivity());
        mWordAdapter = new WordListRecycleAdapter(words);
        mWordRecycleView.setLayoutManager(llm);
        mWordRecycleView.setAdapter(mWordAdapter);
    }

    private void update(List<WordModel> words) {
        mWordAdapter.replaceData(words);
    }
}
