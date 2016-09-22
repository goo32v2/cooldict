package com.goo32v2.cooldict.view.activities;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.goo32v2.cooldict.CoolDictApp;
import com.goo32v2.cooldict.R;
import com.goo32v2.cooldict.data.DictionariesRepository;
import com.goo32v2.cooldict.data.WordsRepository;
import com.goo32v2.cooldict.databinding.ActivityWordsListBinding;
import com.goo32v2.cooldict.models.DictionaryModel;
import com.goo32v2.cooldict.models.WordModel;
import com.goo32v2.cooldict.utils.Navigator;
import com.goo32v2.cooldict.view.WordsListViewModelContract;
import com.goo32v2.cooldict.view.viewmodels.WordsListViewModel;

import java.util.List;

import javax.inject.Inject;

public class WordsListActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        WordsListViewModelContract.View {

    private ActivityWordsListBinding mBinding;
    private WordsListViewModel mViewModel;

    @Inject Navigator navigator;
    @Inject WordsRepository wordsRepository;
    @Inject DictionariesRepository dictionariesRepository;

    private FloatingActionButton mFab;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CoolDictApp.getComponent().inject(this);
        initDataBinding();

        setSupportActionBar(mToolbar);

        mFab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);
    }

    private void initDataBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_words_list);
        mViewModel = new WordsListViewModel(this, getContext());
        mBinding.setWordListViewModel(mViewModel);

        mFab = mBinding.content.fab;
        mToolbar = mBinding.content.toolbar.toolbar;
        mDrawerLayout = mBinding.drawerLayout;
        mNavigationView = mBinding.navView;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_action_show_all:
                break;
            case R.id.nav_action_create_dictionary:
                break;
            case R.id.nav_action_open_dictionary_manager:
                startDictionaryManagerActivity();
                break;
            default:
                selectUserDictionary(item);
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void selectUserDictionary(MenuItem item) {

    }

    private void startDictionaryManagerActivity(){
        Navigator.startDictionaryManagerActivity();
    }

    @Override
    public void loadWords(List<WordModel> words) {

    }

    @Override
    public void loadDictionaries(List<DictionaryModel> dictionaries) {

    }

    @Override
    public Context getContext() {
        return null;
    }
}
