package com.goo32v2.cooldict.addeditword;

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

public class AddEditWordActivity extends AppCompatActivity {

    public static final int REQUEST_ADD_WORD = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_word);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        AddEditWordFragment addEditWordFragment = (AddEditWordFragment)
                getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        String wordId = null;
        if (addEditWordFragment == null) {
            addEditWordFragment = AddEditWordFragment.newInstance();

            if (getIntent().hasExtra(AddEditWordFragment.ARGUMENT_EDIT_WORD_ID)) {
                wordId = getIntent().getStringExtra(AddEditWordFragment.ARGUMENT_EDIT_WORD_ID);
                if (actionBar != null) {
                    actionBar.setTitle(R.string.edit_word);
                }
                Bundle bundle = new Bundle();
                bundle.putString(AddEditWordFragment.ARGUMENT_EDIT_WORD_ID, wordId);
                addEditWordFragment.setArguments(bundle);
            } else {
                assert actionBar != null;
                actionBar.setTitle(R.string.add_new_word_title);
            }
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    addEditWordFragment, R.id.contentFrame);
        }

        new AddEditWordPresenter(wordId, Injection.provideWordRepository(getApplicationContext()),
                addEditWordFragment);
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
