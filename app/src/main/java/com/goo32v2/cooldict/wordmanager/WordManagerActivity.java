package com.goo32v2.cooldict.wordmanager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.goo32v2.cooldict.Injection;
import com.goo32v2.cooldict.R;
import com.goo32v2.cooldict.wordmanager.interfaces.WordManagerPresenterContract;
import com.goo32v2.cooldict.wordmanager.interfaces.WordManagerViewContract;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.data.sources.interfaces.DataSource;

import java.util.List;

public class WordManagerActivity extends AppCompatActivity implements WordManagerViewContract {

    public static final String ARGUMENT_EDIT_WORD = "EDIT_WORD_ID";

    private WordManagerFragment mFragment;
    private WordManagerPresenterContract mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_manage);

        setupActionBar();

        new WordManagerPresenter(
                Injection.provideWordRepository(this),
                Injection.provideDictionaryRepository(this),
                this
        );

        if (getSupportFragmentManager().getFragments().get(0) instanceof WordManagerFragment){
            mFragment = (WordManagerFragment) getSupportFragmentManager().getFragments().get(0);
        }

        if (getIntent().hasExtra(ARGUMENT_EDIT_WORD)){
            mPresenter.populate((WordModel) getIntent().getSerializableExtra(ARGUMENT_EDIT_WORD));
        }

        mPresenter.getDictionaryNames(new DataSource.GetListCallback<String>() {
            @Override
            public void onLoaded(List<String> entries) {
                mFragment.setDictionariesAdapter(entries);
            }

            @Override
            public void onDataNotAvailable() {
                mPresenter.showMessage(getString(R.string.error_cannotGetDictionaries));
            }
        });

        mFragment.setImeAction(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    submit();
                    handled = true;
                }
                return handled;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_submit_word, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_submit_word) {
            submit();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void submit() {
        String wordId = mFragment.getWordId();
        String originalText = mFragment.getOriginalWord();
        String translatedText = mFragment.getTranslatedWord();
        String dictionary = mFragment.getDictionary();

    // translatedText can be empty
        if (getIntent().hasExtra(ARGUMENT_EDIT_WORD)){
            update(wordId, originalText, translatedText, dictionary);
        } else {
            create(originalText, translatedText, dictionary);
        }
    }

    private void update(String id, String originalText, String translatedText, String dictionary){
        if (originalText.isEmpty() || dictionary.isEmpty()){
            mPresenter.showMessage(getString(R.string.empty_fields_error));
        } else {
            mPresenter.update(id, originalText, translatedText, dictionary);
        }
    }

    private void create(String originalText, String translatedText, String dictionary){
        if (originalText.isEmpty() || dictionary.isEmpty()){
            mPresenter.showMessage(getString(R.string.empty_fields_error));
        } else {
            mPresenter.create(originalText, translatedText, dictionary);
        }
    }

    private void setupActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            if (getIntent().hasExtra(ARGUMENT_EDIT_WORD)) {
                actionBar.setTitle(R.string.actionbar_title_edit_word_activity);
            } else {
                actionBar.setTitle(R.string.actionbar_title_add_new_word_activity);
            }
        }

    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(WordManagerActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishActivity() {
        this.setResult(Activity.RESULT_OK);
        this.finish();
    }

    @Override
    public void populateWord(String id, String originalWord, String translatedWrd, String dictionary) {
        mFragment.populate(id, originalWord, translatedWrd, dictionary);
    }

    @Override
    public void setPresenter(WordManagerPresenterContract presenter) {
        this.mPresenter = presenter;
    }
}
