package com.goo32v2.cooldict.worddetails;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.goo32v2.cooldict.Injection;
import com.goo32v2.cooldict.R;
import com.goo32v2.cooldict.utils.ActivityUtils;
import com.goo32v2.cooldict.utils.EspressoIdlingResource;

public class WordDetailActivity extends AppCompatActivity {

    public static final String EXTRA_WORD_ID = "WORD_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        WordDetailFragment wordDetailFragment = (WordDetailFragment)
                getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        String wordId = getIntent().getStringExtra(EXTRA_WORD_ID);
        if (wordDetailFragment == null) {
            wordDetailFragment = WordDetailFragment.newInstance(wordId);
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), wordDetailFragment, R.id.contentFrame);
        }

        new WordDetailPresenter(Injection.provideWordRepository(getApplicationContext()),
                wordDetailFragment, wordId);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @VisibleForTesting
    public IdlingResource getCountingIdlingResource() {
        return EspressoIdlingResource.getIdlingResource();
    }
}
