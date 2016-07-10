package com.goo32v2.cooldict.view.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.goo32v2.cooldict.CoolDictApp;
import com.goo32v2.cooldict.R;
import com.goo32v2.cooldict.data.dtos.Pair;
import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.presenter.impl.DictionaryManagerPresenter;
import com.goo32v2.cooldict.view.DictionaryManagerViewContract;
import com.goo32v2.cooldict.view.dialogs.DialogFabric;
import com.goo32v2.cooldict.view.fragments.DictionaryManagerFragment;

import java.util.List;

import javax.inject.Inject;

/**
 * Created on 29-Jun-16. (c) CoolDict
 */

public class DictionaryManagerActivity extends AppCompatActivity implements DictionaryManagerViewContract {

    @Inject protected DictionaryManagerPresenter mPresenter;
    private DictionaryManagerFragment mFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary_manager);
        CoolDictApp.getComponent().inject(this);

        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (!fragments.isEmpty() && fragments.get(0) instanceof DictionaryManagerFragment){
            mFragment = (DictionaryManagerFragment) fragments.get(0);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.setView(this);
        mPresenter.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dictionary_manager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_create_new_dictionary) {
            createDialog(this).show();
        }

        return super.onOptionsItemSelected(item);
    }

    private AlertDialog.Builder createDialog(Context context) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);

        final EditText dictionaryName = new EditText(context);
        alert.setMessage("Enter Your Message");
        alert.setTitle("Enter Your Title");

        alert.setView(dictionaryName);

        alert.setPositiveButton("Yes Option", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                DictionaryModel model = new DictionaryModel(dictionaryName.getText().toString());
                mPresenter.save(model);
            }
        });

        alert.setNegativeButton("No Option", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });

        return alert;
    }

    @Override
    public void showDictionaries(List<Pair<DictionaryModel, View.OnClickListener>> list) {
        mFragment.showDictionaryList(list);
    }

    @Override
    public AlertDialog getConfirmationDialog(final DictionaryModel model){
        return DialogFabric.getDeleteConfirnationDialog(this,
                getString(R.string.title_delete_confirmation_dialog),
                getString(R.string.message_delete_confirmation_dialog),
                getString(R.string.positive_delete_confirmation_dialog),
                getString(R.string.negative_delete_confirmation_dialog),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.remove(model);
                        // update fragment
                        onResume();
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
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, DictionaryManagerActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
