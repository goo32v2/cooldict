package com.goo32v2.cooldict.words;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.goo32v2.cooldict.R;
import com.goo32v2.cooldict.data.models.ModelDTO;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.words.interfaces.WordViewContract;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 14-May-16. (c) CoolDict
 */

public class WordsFragment extends Fragment implements WordViewContract.FragmentView {

    private WordRecycleAdapter mWordAdapter;
    private LinearLayoutManager llm;
    @BindView(R.id.wordsLL) RecyclerView mWordRecycleView;
    @BindView(R.id.noWords) View mNoWordsView;
    @BindView(R.id.noWordsText) TextView mNoWordsTextView;

    // must be empty
    public WordsFragment(){}

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

    @Override
    public void showWords(List<ModelDTO<WordModel, View.OnClickListener>> words) {
        mNoWordsView.setVisibility(View.GONE);
        mWordRecycleView.setVisibility(View.VISIBLE);

        // if null == first call
        if (llm == null || mWordAdapter == null){
            setupRecycleView(words);
        } else {
            update(words);
        }
    }

    @Override
    public void showNoWords() {
        mWordRecycleView.setVisibility(View.GONE);
        mNoWordsView.setVisibility(View.VISIBLE);

        mNoWordsTextView.setText(getString(R.string.no_word_text));
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    private void setupRecycleView(List<ModelDTO<WordModel, View.OnClickListener>> words){
        llm = new LinearLayoutManager(getActivity());
        mWordAdapter = new WordRecycleAdapter(words);
        mWordRecycleView.setLayoutManager(llm);
        mWordRecycleView.setAdapter(mWordAdapter);
    }

    private void update(List<ModelDTO<WordModel, View.OnClickListener>> words) {
        mWordAdapter.replaceData(words);
    }
}
