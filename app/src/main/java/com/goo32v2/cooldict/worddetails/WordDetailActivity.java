package com.goo32v2.cooldict.worddetails;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.goo32v2.cooldict.Injection;
import com.goo32v2.cooldict.R;
import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.models.WordWithDictionaryDTO;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.data.sources.interfaces.DataSource;
import com.goo32v2.cooldict.editword.EditWordActivity;
import com.goo32v2.cooldict.utils.ActivityUtils;
import com.goo32v2.cooldict.worddetails.interfaces.WordDetailPresenterContract;
import com.goo32v2.cooldict.worddetails.interfaces.WordDetailViewContract;

import java.util.List;

public class WordDetailActivity extends AppCompatActivity implements WordDetailViewContract{

    public static final String EXTRA_WORD_ID = "WORD_ID";
    private WordDetailFragment mFragment;
    private WordDetailPresenterContract mPresenter;
    private WordModel extraWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_detail);

        WordDetailFragment wordDetailFragment = (WordDetailFragment)
                getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        final WordWithDictionaryDTO<WordModel, DictionaryModel> modelsDTO = new WordWithDictionaryDTO<>();
        extraWord = (WordModel) getIntent().getSerializableExtra(EXTRA_WORD_ID);
        modelsDTO.setWord(extraWord);

        new WordDetailPresenter(
                Injection.provideWordRepository(this),
                Injection.provideDictionaryRepository(this),
                this,
                extraWord
        );

        if (wordDetailFragment == null) {
            mPresenter.getDictionary(extraWord.getDictionaryID(), new DataSource.GetListCallback<DictionaryModel>() {
                @Override
                public void onLoaded(List<DictionaryModel> entries) {
                    modelsDTO.setDictionary(entries.get(0));
                }

                @Override
                public void onDataNotAvailable() {
                    // TODO: 28-Jun-16 Have word with dictionary those we haven't got in database
                    Log.i("hello", "nu mlya");
                }
            });

            mFragment = WordDetailFragment.newInstance(modelsDTO);
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(),
                    mFragment,
                    R.id.contentFrame);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_word_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_edit) {
            mPresenter.startEditWordActivity();
            return true;
        }
        if (id == R.id.action_delete) {
            mPresenter.actionDeleteWord();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void startEditWordActivity() {
        Intent intent = new Intent(this, EditWordActivity.class);
        intent.putExtra(EditWordActivity.ARGUMENT_EDIT_WORD, extraWord);
        startActivityForResult(intent, 1);
    }

    @Override
    public void populate() {
        mFragment.populate();
    }

    @Override
    public void finishActivity() {
        this.setResult(Activity.RESULT_OK);
        this.finish();
    }

    @Override
    public void setPresenter(WordDetailPresenterContract presenter) {
        mPresenter = presenter;
    }
}
