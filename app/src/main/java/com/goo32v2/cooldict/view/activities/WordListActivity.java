package com.goo32v2.cooldict.view.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.EditText;

import com.goo32v2.cooldict.CoolDictApp;
import com.goo32v2.cooldict.R;
import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.presenter.impl.WordListPresenter;
import com.goo32v2.cooldict.view.WordListViewContract;
import com.goo32v2.cooldict.view.fragments.WordListFragment;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

// TODO: 17-May-16 think about sorting words
public class WordListActivity extends AppCompatActivity implements WordListViewContract,
        NavigationView.OnNavigationItemSelectedListener {

    public static String EXTRA_DICTIONARY = "dictionary";

    @Inject protected WordListPresenter mPresenter;
    private WordListFragment mFragment;

    @BindView(R.id.coordinatorLayout) CoordinatorLayout mCoordinatorLayout;
    @BindView(R.id.nav_view) NavigationView mNavigationView;
    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.fab) FloatingActionButton floatingActionButton;

    private DictionaryModel activeDictionary;
    private Map<String, DictionaryModel> menuDataMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        CoolDictApp.getComponent().inject(this);
        ButterKnife.bind(this);
        mPresenter.onCreate(this);

        // get all created fragments and assign it to vars
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            if (fragment instanceof WordListFragment) {
                this.mFragment = (WordListFragment) fragment;
            }
        }

        setupView();

        if (savedInstanceState != null) {
            savedInstanceState.getSerializable(EXTRA_DICTIONARY);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_DICTIONARY, activeDictionary);
        mPresenter.onResume(bundle);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(EXTRA_DICTIONARY, activeDictionary);
        super.onSaveInstanceState(outState);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_words, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.action_settings) {
//            // for single entry point
//            mPresenter.navigateToSettingsActivity();
//            return true;
//        }
//        if (id == R.id.action_dictionary_manager) {
//            mPresenter.navigateToDictionaryManagerActivity();
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.words_all:
                activeDictionary = null;
                mPresenter.getWords(null);
                setTitle(item.getTitle());
                break;

            case R.id.action_create_new_dictionary:
                createDialog().show();
                break;

            case R.id.action_dictionary_manager:
                mPresenter.navigateToDictionaryManagerActivity();
                break;

            case R.id.action_settings:
                mPresenter.navigateToSettingsActivity();
                break;

            default:
                activeDictionary = menuDataMap.get(item.getTitle().toString());
                mPresenter.getWords(activeDictionary);
                setTitle(item.getTitle());
                break;
        }
        item.setChecked(true);
        mDrawerLayout.closeDrawers();
        return true;
    }

    @Override
    public void setDictionaryMenuData(Map<String, DictionaryModel> entries) {
        this.menuDataMap = entries;
        mNavigationView.inflateMenu(R.menu.menu_words);
        Menu menu = mNavigationView.getMenu();

//        LayoutInflater inflater = getLayoutInflater();
//        View header = inflater.inflate(R.layout.nav_dictionary_header,null);
        MenuItem dictionaryItem = menu.findItem(R.id.dictionaries);
        dictionaryItem.setTitle(getString(R.string.nav_dictionary_header_title));


        SubMenu dictionarySubMenu = dictionaryItem.getSubMenu();

        for (String item : entries.keySet()) {
            MenuItem menuItem = dictionarySubMenu.add(item);
            menuItem.setIcon(R.drawable.ic_nav_item);
            menuItem.setCheckable(true);
        }
    }

    @Override
    public void clearDictionaryMenuData() {
        mNavigationView.getMenu().clear();
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_SHORT);
    }

    @Override
    public void showLoading(boolean trigger) {
        // TODO: 11-Jul-16 implement
    }

    @Override
    public void finishActivity() {
        this.setResult(Activity.RESULT_OK);
        this.finish();
    }

    @Override
    public void showList(List<Pair<WordModel, View.OnClickListener>> words) {
        mFragment.showWords(words);
    }

    @Override
    public void showNoWords() {
        mFragment.showNoWords();
    }

    private AlertDialog createDialog(){
        final View dialogView = getLayoutInflater().inflate(R.layout.dialog_create_dictionary, null);

        DialogInterface.OnClickListener positiveListener = new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EditText editText = (EditText) dialogView.findViewById(R.id.dictionary_name);
                mPresenter.actionCreateDictionary(editText.getText().toString());
                clearDictionaryMenuData();
                mPresenter.setDictionaryMenuData();
            }
        };

        DialogInterface.OnClickListener negativeListener = new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        };

        return new AlertDialog.Builder(this)
                .setView(dialogView)
                .setTitle(getString(R.string.dialog_dictionary_creation_title))
                .setPositiveButton(getString(R.string.dialog_dictionary_creation_create), positiveListener)
                .setNegativeButton(getString(R.string.dialog_dictionary_creation_cancel), negativeListener)
                .create();
    }

    private void setupDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void setupView() {
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.show_all_words));
        setupDrawerLayout();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // for single entry point
                mPresenter.navigateToWordManagerActivity();
            }
        });
        mNavigationView.setNavigationItemSelectedListener(this);
    }
}
