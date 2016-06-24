package com.goo32v2.cooldict.words;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.goo32v2.cooldict.Injection;
import com.goo32v2.cooldict.R;
import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.data.sources.interfaces.DataSource;
import com.goo32v2.cooldict.utils.ActivityUtils;
import com.goo32v2.cooldict.words.interfaces.WordPresenterContract;
import com.goo32v2.cooldict.words.interfaces.WordViewContract;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WordsActivity extends AppCompatActivity implements WordViewContract{

    private WordsPresenter mWordsPresenter;
    private WordsFragment mWordsFragment;
    private NavDrawerFragment mNavDrawerFragment;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @BindView(R.id.navList) ListView mNavigationLV;
    @BindView(R.id.fab) FloatingActionButton floatingActionButton;

    private static final String CURRENT_DICTIONARY = "CURRENT_DICTIONARY";
    private static boolean isAnyFragmentSetup = false;


    // TODO: 17-May-16 think about sorting words with rating
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        new WordsPresenter(
                Injection.provideWordRepository(getApplicationContext()),
                Injection.provideDictionaryRepository(getApplicationContext()),
                this
        );

        setupView();

        // get all created fragments and assign it to vars
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            if (fragment instanceof WordsFragment){
                this.mWordsFragment = (WordsFragment) fragment;
            }
            if (fragment instanceof NavDrawerFragment){
                this.mNavDrawerFragment = (NavDrawerFragment) fragment;
            }
        }

        setNewFragment(null);

        if (savedInstanceState != null){
            // TODO: 17-May-16 Get serialized current dictionary from navigation drawer
            savedInstanceState.getSerializable(CURRENT_DICTIONARY);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // TODO: 24-Jun-16 change to NavDrawer Update
        // mNavDrawerFragment.update();
        setupDrawerContent();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO: 17-May-16 Save state (dictionary? or implement some kind of sorting?)
//        outState.putSerializable(CURRENT_DICTIONARY, mWordsPresenter.getAllDictionaries());

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
            mWordsPresenter.startSettingsActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void startSettingsActivity() {
//        Intent intent = new Intent(this, SettingsActivity.class);
//        startActivityForResult(intent, SettingsActivity.REQUEST_SETTINGS);
    }

    @Override
    public void startAddWordActivity() {
//        Intent intent = new Intent(this, AddEditWordFragment.class);
//        startActivity(intent);
    }

    @Override
    public void startWordDetailActivity(WordModel word) {
//        Intent intent = new Intent(getContext(), WordDetailActivity.class);
//        intent.putExtra(WordDetailActivity.EXTRA_WORD_ID, word.getId());
//        startActivity(intent);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(WordsActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(WordPresenterContract presenter) {
        this.mWordsPresenter = (WordsPresenter) presenter;
    }


    private void setupView(){
        setContentView(R.layout.activity_words);
        setSupportActionBar(toolbar);
        setupDrawer(toolbar);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // for single entry point
                mWordsPresenter.startAddNewWordActivity();
            }
        });
    }

    private void setupDrawer(Toolbar toolbar){
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

    private void setupDrawerContent() {
        mWordsPresenter.getAllDictionaries(new DataSource.GetListCallback<DictionaryModel>() {
            @Override
            public void onLoaded(List<DictionaryModel> entry) {
                mNavDrawerFragment.setupNavigationDrawer(entry);
            }

            @Override
            public void onDataNotAvailable() {
                // TODO: 24-Jun-16 Implement presenter method with show message
            }
        });
    }

    @Deprecated
    private void setNewFragment(String dictName){

        // TODO: 22-Jun-16 optimize this by checking data not changed
        WordsFragment fragment = WordsFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putString(WordsFragment.DICTIONARY_NAME, dictName);
        fragment.setArguments(bundle);

        if (!isAnyFragmentSetup){
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment,
                    R.id.contentFrame);
            isAnyFragmentSetup = true;
        } else {
            ActivityUtils.replaceFragmentInActivity(getSupportFragmentManager(),
                    fragment,
                    R.id.contentFrame);
        }
    }

}
