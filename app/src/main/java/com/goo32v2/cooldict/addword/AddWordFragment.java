package com.goo32v2.cooldict.addword;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.goo32v2.cooldict.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddWordFragment extends Fragment {

    @BindView(R.id.fab_changes_done) FloatingActionButton fab;
    @BindView(R.id.ea_original_word) TextView mOriginalWord;
    @BindView(R.id.ea_translated_word) TextView mTranslatedWord;
    @BindView(R.id.ea_dictionary_id) AutoCompleteTextView mDictionary;

    public AddWordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_word, container, false);
        ButterKnife.bind(this, root);

        return root;
    }

    public void setFabOnClickListener(@NonNull View.OnClickListener listener) {
        fab.setOnClickListener(listener);
    }

    public void setDictionariesAdapter(List<String> names){
        mDictionary.setAdapter(
                new ArrayAdapter<>(
                        getActivity(),
                        R.layout.item_auto_complete_popup,
                        new ArrayList<>(names)
                )
        );
    }
}
