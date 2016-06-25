package com.goo32v2.cooldict.addeditword;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.goo32v2.cooldict.Injection;
import com.goo32v2.cooldict.R;
import com.goo32v2.cooldict.addeditword.interfaces.AddEditWordPresenterContract;
import com.goo32v2.cooldict.addeditword.interfaces.AddEditWordViewContract;

import java.util.List;

import butterknife.ButterKnife;

public class AddEditWordActivity extends AppCompatActivity implements AddEditWordViewContract {


    private AddEditWordPresenterContract mPresenter;
    private AddEditWordFragment addEditWordFragment;
    private ActionBar actionBar;
    private String wordId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_word);

        setupActionBar();

        ButterKnife.bind(this);

        // How many fragments available here? interesting
        List<android.support.v4.app.Fragment> fragments = getSupportFragmentManager().getFragments();
        if (!fragments.isEmpty() && fragments.get(0) instanceof AddEditWordFragment) {
            addEditWordFragment = (AddEditWordFragment) fragments.get(0);
        }

        new AddEditWordPresenter(wordId,
                Injection.provideWordRepository(getApplicationContext()),
                Injection.provideDictionaryRepository(getApplicationContext()),
                addEditWordFragment);

        if (getIntent().hasExtra(AddEditWordFragment.ARGUMENT_EDIT_WORD_ID)) {
            wordId = getIntent().getStringExtra(AddEditWordFragment.ARGUMENT_EDIT_WORD_ID);
        }

        // setup fragment view
        if (wordId == null) {
            mPresenter.setupAddView();
        } else {
            mPresenter.setupEditView();
        }

        reattachFragment(addEditWordFragment);
    }

    private void reattachFragment(android.support.v4.app.Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .detach(fragment)
                .attach(fragment)
                .commit();
    }



    @Override
    public void setupAddView() {
        actionBar.setTitle(R.string.actionbar_title_add_new_word_activity);
        addEditWordFragment.setupAddView();
    }

    @Override
    public void setupEditView() {
        actionBar.setTitle(R.string.actionbar_title_edit_word_activity);
        Bundle bundle = new Bundle();
        bundle.putString(AddEditWordFragment.ARGUMENT_EDIT_WORD_ID, wordId);
        addEditWordFragment.setArguments(bundle);
        addEditWordFragment.setupEditView();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(AddEditWordActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishActivity() {
        this.setResult(Activity.RESULT_OK);
        this.finish();
    }

    @Override
    public void setPresenter(AddEditWordPresenterContract presenter) {
        this.mPresenter = presenter;
    }

    private void setupActionBar(){
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }
}
