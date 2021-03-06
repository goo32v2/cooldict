package com.goo32v2.cooldict.view.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.goo32v2.cooldict.R;
import com.goo32v2.cooldict.data.models.WordModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WordDetailFragment extends Fragment {

    @BindView(R.id.original_word) TextView mOriginalWord;
    @BindView(R.id.translated_word) TextView mTranslatedWord;
    @BindView(R.id.dictionary_id) TextView mDictionaryTitle;


    public static WordDetailFragment newInstance() {
        return new WordDetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_word_detail, container, false);
        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onPause() {
        super.onPause();
        getFragmentManager().beginTransaction().remove(this).commit();
    }

    public void populate(WordModel model) {
        mOriginalWord.setText(model.getOriginalWord());
        mTranslatedWord.setText(model.getTranslatedWord());
        mDictionaryTitle.setText(model.getDictionaryTitle());
    }
}
