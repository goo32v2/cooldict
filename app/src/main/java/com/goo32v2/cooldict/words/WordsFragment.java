package com.goo32v2.cooldict.words;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.goo32v2.cooldict.R;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.words.interfaces.WordPresenterContract;
import com.goo32v2.cooldict.words.interfaces.WordViewContract;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created on 14-May-16. (c) CoolDict
 */

public class WordsFragment extends Fragment implements WordViewContract.FragmentView {

    public static String DICTIONARY_NAME = "dictionaryName";
    private WordPresenterContract mPresenter;
    private WordRecycleAdapter mWordAdapter;
    @BindView(R.id.wordsLL) RecyclerView mWordRecycleView;
    @BindView(R.id.noWords) View mNoWordsView;
    @BindView(R.id.noWordsText) TextView mNoWordsTextView;
    @BindView(R.id.noWordsAdd) TextView mNoWordsAddView;

    // must be empty
    public WordsFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_words, container, false);
        ButterKnife.bind(this, root);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
        mWordAdapter = new WordRecycleAdapter(new ArrayList<WordModel>(0), mPresenter);
        mWordRecycleView.setLayoutManager(llm);
        mWordRecycleView.setAdapter(mWordAdapter);

        mNoWordsAddView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddWord();
            }
        });


        mPresenter.loadWords();
        setHasOptionsMenu(true);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start(getArguments().getString(DICTIONARY_NAME));
    }

    @Override
    public void setPresenter(WordPresenterContract presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showWords(List<WordModel> words) {
        mWordAdapter.replaceData(words);
        mWordRecycleView.setVisibility(View.VISIBLE);
        mNoWordsView.setVisibility(View.GONE);
    }

    @Override
    public void showAddWord() {
//        Intent intent = new Intent(getContext(), AddEditWordActivity.class);
//        startActivityForResult(intent, AddEditWordActivity.REQUEST_ADD_WORD);
    }

    @Override
    public void showWordDetailUi(WordModel word) {
//        Intent intent = new Intent(getContext(), WordDetailActivity.class);
//        intent.putExtra(WordDetailActivity.EXTRA_WORD_ID, word.getId());
//        startActivity(intent);
    }

    @Override
    public void showMessage(String msg) {
        if (getView() == null) {
            return;
        }
        Snackbar.make(getView(), msg, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showError(String msg) {
        showMessage(getString(R.string.words_loading_error_text));
    }

    @Override
    public void showNoWords() {
        showNoWordsView(getString(R.string.no_word_text), false);
    }

    private void showNoWordsView(String mainText, boolean showAddView) {
        mWordRecycleView.setVisibility(View.GONE);
        mNoWordsView.setVisibility(View.VISIBLE);

        mNoWordsTextView.setText(mainText);
        mNoWordsAddView.setVisibility(showAddView ? View.VISIBLE : View.GONE);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
