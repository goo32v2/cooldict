package com.goo32v2.cooldict.view.activities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.goo32v2.cooldict.CoolDictApp;
import com.goo32v2.cooldict.R;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.presenter.impl.WordDetailPresenter;
import com.goo32v2.cooldict.utils.ActivityUtils;
import com.goo32v2.cooldict.view.WordDetailViewContract;
import com.goo32v2.cooldict.view.dialogs.DialogFabric;
import com.goo32v2.cooldict.view.fragments.WordDetailFragment;

import javax.inject.Inject;

public class WordDetailActivity extends AppCompatActivity implements WordDetailViewContract {

    public static final String EXTRA_WORD = "WORD_ID";

    private WordDetailFragment mFragment;
    private WordModel extraWord;

    @Inject protected WordDetailPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_detail);
        CoolDictApp.getComponent().inject(this);

        mPresenter.setView(this);

        this.mFragment = WordDetailFragment.newInstance();
        ActivityUtils.addFragmentToActivity(
                getSupportFragmentManager(),
                mFragment,
                R.id.contentFrame);

    }

    @Override
    public WordModel getExtraWord() {
        this.extraWord = (WordModel) getIntent().getSerializableExtra(EXTRA_WORD);
        return extraWord;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.finish();
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
            mPresenter.navigateToWordManagerActivity(extraWord);
            return true;
        }
        if (id == R.id.action_delete) {
            askConfirmation().show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private AlertDialog askConfirmation(){
        return DialogFabric.getDeleteConfirnationDialog(this,
                getString(R.string.title_delete_confirmation_dialog),
                getString(R.string.message_delete_confirmation_dialog),
                getString(R.string.positive_delete_confirmation_dialog),
                getString(R.string.negative_delete_confirmation_dialog),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.actionDeleteWord(extraWord);
                    }
                },
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
    }

    @Override
    public void populate(WordModel model) {
        mFragment.populate(model);
    }

    @Override
    public void finishActivity() {
        this.setResult(Activity.RESULT_OK);
        this.finish();
    }

    public static void startActivity(Context context, WordModel model) {
        Intent intent = new Intent(context, WordDetailActivity.class);
        intent.putExtra(WordDetailActivity.EXTRA_WORD, model);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
