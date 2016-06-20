package com.goo32v2.cooldict.worddetails;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.goo32v2.cooldict.R;
import com.goo32v2.cooldict.addeditword.AddEditWordActivity;
import com.goo32v2.cooldict.addeditword.AddEditWordFragment;
import com.goo32v2.cooldict.worddetails.interfaces.WordDetailPresenterContract;
import com.goo32v2.cooldict.worddetails.interfaces.WordDetailViewContract;

import static com.google.common.base.Preconditions.checkNotNull;

public class WordDetailFragment extends Fragment implements WordDetailViewContract {

    public static final String ARGUMENT_WORD_ID = "WORD_ID";
    public static final int REQUEST_EDIT_WORD = 1;

    private WordDetailPresenterContract mPresenter;

    private TextView mOriginalWord;
    private TextView mTranslatedWord;
    private TextView mDictionaryId;


    public static WordDetailFragment newInstance(String wordId) {
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT_WORD_ID, wordId);
        WordDetailFragment fragment = new WordDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_word_detail, container, false);
        setHasOptionsMenu(true);

        mOriginalWord = (TextView) root.findViewById(R.id.original_word);
        mTranslatedWord = (TextView) root.findViewById(R.id.translated_word);
        mDictionaryId = (TextView) root.findViewById(R.id.dictionary_id);

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab_edit_word);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.editWord();
            }
        });

        return root;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_delete:
                mPresenter.deleteWord();
                return true;
        }
        return false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_word_detail, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void showLoadingIndicator(boolean active) {
        // TODO: 16-Jun-16 ?????
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showEditWord(String id) {
        Intent intent = new Intent(getContext(), AddEditWordActivity.class);
        intent.putExtra(AddEditWordFragment.ARGUMENT_EDIT_WORD_ID, id);
        startActivityForResult(intent, REQUEST_EDIT_WORD);
    }

    @Override
    public void showDeleteWord() {
        getActivity().finish();
    }

    @Override
    public void showMissingWord() {
        mOriginalWord.setText(R.string.no_data);
        mTranslatedWord.setText(R.string.no_data);
        mDictionaryId.setText(R.string.no_data);
    }

    @Override
    public void showOriginalWord(String entry) {
        mOriginalWord.setVisibility(View.VISIBLE);
        mOriginalWord.setText(entry);
    }

    @Override
    public void hideOriginalWord() {
        mOriginalWord.setVisibility(View.GONE);
    }

    @Override
    public void showTranslatedWord(String entry) {
        mTranslatedWord.setVisibility(View.VISIBLE);
        mTranslatedWord.setText(entry);
    }

    @Override
    public void hideTranslatedWord() {
        mTranslatedWord.setVisibility(View.GONE);

    }

    @Override
    public void showDictionaryId(String entry) {
        mDictionaryId.setVisibility(View.VISIBLE);
        mDictionaryId.setText(entry);
    }

    @Override
    public void setPresenter(WordDetailPresenterContract presenter) {
        mPresenter = checkNotNull(presenter);
    }
}
