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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.goo32v2.cooldict.Injection;
import com.goo32v2.cooldict.R;
import com.goo32v2.cooldict.addeditword.interfaces.AddEditWordPresenterContract;
import com.goo32v2.cooldict.addeditword.interfaces.AddEditWordViewContract;
import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.sources.DictionaryRepository;
import com.goo32v2.cooldict.data.sources.interfaces.DataSource;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;


public class AddEditWordFragment extends Fragment implements AddEditWordViewContract, DataSource.GetListCallback<DictionaryModel>
{

    public static final String ARGUMENT_EDIT_WORD_ID = "EDIT_WORD_ID";

    private AddEditWordPresenterContract mPresenter;

    private TextView mWordId;
    private TextView mOriginalWord;
    private TextView mTranslatedWord;
    private AutoCompleteTextView mDictionary;
    private String mEditWordId;

    private DictionaryRepository dictionaryRepository;
    private List<DictionaryModel> dictionaries;

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
                            mDictionary.getText().toString()
                    );
                } else {
                    mPresenter.updateWord(
                            mWordId.getText().toString(),
                            mOriginalWord.getText().toString(),
                            mTranslatedWord.getText().toString(),
                            mDictionary.getText().toString()
                    );
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.dictionaryRepository = Injection.provideDictionaryRepository(getActivity().getApplicationContext());
        View root = inflater.inflate(R.layout.fragment_add_edit_word, container, false);

        mWordId = (TextView) root.findViewById(R.id.ea_word_id);
        mOriginalWord = (TextView) root.findViewById(R.id.ea_original_word);
        mTranslatedWord = (TextView) root.findViewById(R.id.ea_translated_word);
        mDictionary = (AutoCompleteTextView) root.findViewById(R.id.ea_dictionary_id);

        ArrayAdapter<String> dictionaryAdapter = new ArrayAdapter<>(
                getActivity().getApplicationContext(),
                R.layout.auto_complete_item,
                getDictionaryNames());

        mDictionary.setAdapter(dictionaryAdapter);

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
    public void setDictionary(String dictionary) {
        mDictionary.setText(dictionary);
    }

    private List<String> getDictionaryNames(){
        dictionaryRepository.get(this);
        List<String> result = new ArrayList<>();
        for (DictionaryModel model : dictionaries) {
            result.add(model.getTitle());
        }
        return result;
    }

    @Override
    public void onLoaded(List<DictionaryModel> entry) {
        this.dictionaries = entry;
    }

    @Override
    public void onDataNotAvailable() {

    }
}
