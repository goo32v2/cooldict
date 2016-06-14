package com.goo32v2.cooldict.addeditword;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.goo32v2.cooldict.R;
import com.goo32v2.cooldict.addeditword.interfaces.AddEditWordPresenterContract;
import com.goo32v2.cooldict.addeditword.interfaces.AddEditWordViewContract;

import static com.google.common.base.Preconditions.checkNotNull;


public class AddEditWordFragment extends Fragment implements AddEditWordViewContract {

    public static final String ARGUMENT_EDIT_WORD_ID = "EDIT_WORD_ID";

    private AddEditWordPresenterContract mPresenter;

    private TextView mWordId;
    private TextView mOriginalWord;
    private TextView mTranslatedWord;
    private TextView mDictionaryId;

    private String mEditWordId;

    public static AddEditWordFragment newInstance() {
        return new AddEditWordFragment();
    }

    public AddEditWordFragment(){
        //empty
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull AddEditWordPresenterContract presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setWordIdIfAny();

        final FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab_changes_done);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNewWord()){
                    mPresenter.createWord(
                            mOriginalWord.getText().toString(),
                            mTranslatedWord.getText().toString(),
                            mDictionaryId.getText().toString()
                    );
                } else {
                    mPresenter.updateWord(
                            mWordId.getText().toString(),
                            mOriginalWord.getText().toString(),
                            mTranslatedWord.getText().toString(),
                            mDictionaryId.getText().toString()
                    );
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_add_edit_word, container, false);

        mWordId = (TextView) root.findViewById(R.id.ea_word_id);
        mOriginalWord = (TextView) root.findViewById(R.id.ea_original_word);
        mTranslatedWord = (TextView) root.findViewById(R.id.ea_translated_word);
        mDictionaryId = (TextView) root.findViewById(R.id.ea_dictionary_id);

        setHasOptionsMenu(true);
        setRetainInstance(true);
        return root;
    }

    private boolean isNewWord() {
        return mEditWordId == null;
    }

    private void setWordIdIfAny() {
        if (getArguments() != null && getArguments().containsKey(ARGUMENT_EDIT_WORD_ID)){
            mEditWordId = getArguments().getString(ARGUMENT_EDIT_WORD_ID);
        }
    }

    @Override
    public void showEmptyWordError() {
        Snackbar.make(mWordId, R.string.ea_not_empty_word_error_text, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showWordList() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setWordId(String id) {
        mWordId.setText(id);
    }

    @Override
    public void setOriginalWord(String originalWord) {
        mOriginalWord.setText(originalWord);
    }

    @Override
    public void setTranslatedWord(String translatedWord) {
        mTranslatedWord.setText(translatedWord);
    }

    @Override
    public void setDictionaryId(String dictionaryID) {
        mDictionaryId.setText(dictionaryID);
    }
}
