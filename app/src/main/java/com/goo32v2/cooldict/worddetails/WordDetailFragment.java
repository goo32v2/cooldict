package com.goo32v2.cooldict.worddetails;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.goo32v2.cooldict.R;
import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.models.WordWithDictionaryDTO;
import com.goo32v2.cooldict.data.models.WordModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WordDetailFragment extends Fragment {

    public static final String ARGUMENT_WORD_ID = "WORD";
    public static final String ARGUMENT_DICTIONARY = "DICTIONARY";

    @BindView(R.id.original_word) TextView mOriginalWord;
    @BindView(R.id.translated_word) TextView mTranslatedWord;
    @BindView(R.id.dictionary_id) TextView mDictionaryId;


    public static WordDetailFragment newInstance(WordWithDictionaryDTO<WordModel, DictionaryModel> modelsDTO) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARGUMENT_WORD_ID, modelsDTO.getWord());
        bundle.putSerializable(ARGUMENT_DICTIONARY, modelsDTO.getDictionary());
        WordDetailFragment fragment = new WordDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
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

    public void populate() {
        if (getArguments().containsKey(ARGUMENT_WORD_ID)){
            WordModel word = (WordModel) getArguments().getSerializable(ARGUMENT_WORD_ID);
            if (word != null) {
                mOriginalWord.setText(word.getOriginalWord());
                mTranslatedWord.setText(word.getTranslatedWord());

                if (getArguments().containsKey(ARGUMENT_DICTIONARY)){
                    DictionaryModel dict = (DictionaryModel) getArguments().getSerializable(ARGUMENT_DICTIONARY);
                    if (dict != null) {
                        mDictionaryId.setText(dict.getTitle());
                    }
                } else {
                    // we have word, that assign to dictionary, that we haven't got
                    mDictionaryId.setText(word.getDictionaryID());
                }
            }
        }

    }
}
