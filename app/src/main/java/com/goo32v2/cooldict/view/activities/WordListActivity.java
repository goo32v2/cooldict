package com.goo32v2.cooldict.view.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.goo32v2.cooldict.CoolDictApp;
import com.goo32v2.cooldict.R;
import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.presenter.impl.WordListPresenter;
import com.goo32v2.cooldict.view.WordListViewContract;
import com.goo32v2.cooldict.view.fragments.WordListFragment;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WordListActivity extends AppCompatActivity implements WordListViewContract,
        NavigationView.OnNavigationItemSelectedListener {

    @Inject protected WordListPresenter mPresenter;
    private WordListFragment mFragment;

    @BindView(R.id.nav_view) NavigationView mNavigationView;
    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.fab) FloatingActionButton floatingActionButton;

    private String activeDictionary;


    // TODO: 17-May-16 think about sorting words with rating
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        CoolDictApp.getComponent().inject(this);
        ButterKnife.bind(this);
        mPresenter.setView(this);

        // get all created fragments and assign it to vars
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            if (fragment instanceof WordListFragment) {
                this.mFragment = (WordListFragment) fragment;
            }
        }

        setupView();

        if (savedInstanceState != null) {
            // TODO: 17-May-16 Get serialized current dictionary from navigation drawer
            savedInstanceState.getSerializable(activeDictionary);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start(activeDictionary);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mNavigationView.getMenu().clear();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO: 17-May-16 Save state (dictionary? or implement some kind of sorting?)
//        outState.putSerializable(CURRENT_DICTIONARY, mPresenter.getAllDictionaries());

        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_words, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            // for single entry point
            mPresenter.navigateToSettingsActivity();
            return true;
        }
        if (id == R.id.action_dictionary_manager) {
            mPresenter.navigateToDictionaryManagerActivity();
        }

        return super.onOptionsItemSelected(item);
    }

    // TODO: 08-Jul-16 create map and add keys only
    @Override
    public void setMenu(List<DictionaryModel> entries) {
        Menu m = mNavigationView.getMenu();
        m.add(R.string.show_all_words);
        for (DictionaryModel item : entries) {
            m.add(item.getTitle());
        }
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(WordListActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showList(List<WordModel> words) {
        mFragment.showWords(words);
    }

    @Override
    public void showNoWords() {
        mFragment.showNoWords();
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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        setTitle(item.getTitle());
        if (Objects.equals(item.getTitle().toString(), getString(R.string.show_all_words))) {
            // get all words
            activeDictionary = null;
            mPresenter.getWords(null);
        } else {
            activeDictionary = item.getTitle().toString();
            mPresenter.getWords(activeDictionary);
        }
        mDrawerLayout.closeDrawers();
        return true;
    }
}
