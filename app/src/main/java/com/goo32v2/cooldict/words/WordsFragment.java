package com.goo32v2.cooldict.words;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.goo32v2.cooldict.R;
import com.goo32v2.cooldict.addeditword.AddEditWordActivity;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.worddetails.WordDetailActivity;
import com.goo32v2.cooldict.words.interfaces.WordPresenterContract;
import com.goo32v2.cooldict.words.interfaces.WordViewContract;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created on 14-May-16. (c) CoolDict
 */

public class WordsFragment extends Fragment implements WordViewContract {

    public static String DICTIONARY_NAME = "dictionaryName";
    private WordPresenterContract mPresenter;
    private WordRecycleAdapter mWordAdapter;
    private RecyclerView mWordRecycleView;
    private View mNoWordsView;
    private ImageView mNoWordsIcon;
    private TextView mNoWordsTextView;
    private TextView mNoWordsAddView;

    // must be empty
    public WordsFragment(){}

    public static WordsFragment newInstance() {
        return new WordsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start(getArguments().getString(DICTIONARY_NAME));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_words, container, false);

        mWordRecycleView = (RecyclerView) root.findViewById(R.id.wordsLL);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
        mWordAdapter = new WordRecycleAdapter(new ArrayList<WordModel>(0), mPresenter);
        mWordRecycleView.setLayoutManager(llm);
        mWordRecycleView.setAdapter(mWordAdapter);

        mNoWordsView = root.findViewById(R.id.noWords);
        mNoWordsIcon = (ImageView) root.findViewById(R.id.noWordsIcon);
        mNoWordsTextView = (TextView) root.findViewById(R.id.noWordsText);
        mNoWordsAddView = (TextView) root.findViewById(R.id.noWordsAdd);
        mNoWordsAddView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddWord();
            }
        });

        FloatingActionButton floatingActionButton = (FloatingActionButton)
                getActivity().findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.addNewWord();
            }
        });

        final SwipeToRefreshLayout swipeToRefreshLayout = (SwipeToRefreshLayout)
                root.findViewById(R.id.refresh_layout);
        swipeToRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );
        swipeToRefreshLayout.setScrollUpChild(mWordRecycleView);

        swipeToRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadWords(false);
            }
        });

        setHasOptionsMenu(true);
        return root;
    }

    @Override
    public void setPresenter(WordPresenterContract presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setLoadingIndicator(final boolean active) {
        if (getView() == null) {
            return;
        }

        final SwipeToRefreshLayout swipeToRefreshLayout = (SwipeToRefreshLayout)
                getView().findViewById(R.id.refresh_layout);

        swipeToRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeToRefreshLayout.setRefreshing(active);
            }
        });
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
//        mNoWordsIcon.setImageDrawable(getResources().getDrawable(iconRes));
        mNoWordsAddView.setVisibility(showAddView ? View.VISIBLE : View.GONE);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
