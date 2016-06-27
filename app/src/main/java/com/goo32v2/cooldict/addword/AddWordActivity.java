package com.goo32v2.cooldict.addword;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.goo32v2.cooldict.Injection;
import com.goo32v2.cooldict.R;
import com.goo32v2.cooldict.addword.interfaces.AddWordPresenterContract;
import com.goo32v2.cooldict.addword.interfaces.AddWordViewContract;
import com.goo32v2.cooldict.data.sources.interfaces.DataSource;

import java.util.List;

public class AddWordActivity extends AppCompatActivity implements AddWordViewContract{

    private AddWordFragment mAddWordFragment;
    private AddWordPresenterContract mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        setupActionBar();

        new AddWordPresenter(
                Injection.provideWordRepository(this),
                Injection.provideDictionaryRepository(this),
                this
        );

        if (getSupportFragmentManager().getFragments().get(0) instanceof AddWordFragment){
            mAddWordFragment = (AddWordFragment) getSupportFragmentManager().getFragments().get(0);
        }

        mPresenter.getDictionaryNames(new DataSource.GetListCallback<String>() {
            @Override
            public void onLoaded(List<String> entries) {
                mAddWordFragment.setDictionariesAdapter(entries);
            }

            @Override
            public void onDataNotAvailable() {
                mPresenter.showMessage(getString(R.string.error_cannotGetDictionaries));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_words, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add_word) {
            saveWord();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void saveWord() {
        String originalText = mAddWordFragment.mOriginalWord.getText().toString();
        String translatedText = mAddWordFragment.mTranslatedWord.getText().toString();
        String dictionary = mAddWordFragment.mDictionary.getText().toString();

        mPresenter.createWord(originalText, translatedText, dictionary);
    }


    private void setupActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.actionbar_title_add_new_word_activity);
        }

    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(AddWordActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishActivity() {
        this.setResult(Activity.RESULT_OK);
        this.finish();
    }

    @Override
    public void setPresenter(AddWordPresenterContract presenter) {
        this.mPresenter = presenter;
    }
}
