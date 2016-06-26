package com.goo32v2.cooldict.words;

import android.content.Intent;
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
import android.widget.Toast;

import com.goo32v2.cooldict.Injection;
import com.goo32v2.cooldict.R;
import com.goo32v2.cooldict.addword.AddWordActivity;
import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.models.ModelDTO;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.data.sources.interfaces.DataSource;
import com.goo32v2.cooldict.settings.SettingsActivity;
import com.goo32v2.cooldict.words.interfaces.WordPresenterContract;
import com.goo32v2.cooldict.words.interfaces.WordViewContract;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WordsActivity extends AppCompatActivity implements WordViewContract{

    private WordsPresenter mWordsPresenter;
    private WordsFragment mWordsFragment;
    private NavDrawerFragment mNavDrawerFragment;

    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.fab) FloatingActionButton floatingActionButton;

    private static final String CURRENT_DICTIONARY = "CURRENT_DICTIONARY";


    // TODO: 17-May-16 think about sorting words with rating
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new WordsPresenter(
                Injection.provideWordRepository(getApplicationContext()),
                Injection.provideDictionaryRepository(getApplicationContext()),
                this
        );

        setContentView(R.layout.activity_words);

        // get all created fragments and assign it to vars
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            if (fragment instanceof WordsFragment){
                this.mWordsFragment = (WordsFragment) fragment;
            }
            if (fragment instanceof NavDrawerFragment){
                this.mNavDrawerFragment = (NavDrawerFragment) fragment;
            }
        }

        ButterKnife.bind(this);

        setupView();

        if (savedInstanceState != null){
            // TODO: 17-May-16 Get serialized current dictionary from navigation drawer
            savedInstanceState.getSerializable(CURRENT_DICTIONARY);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWordsPresenter.start();

        List<WordModel> recentWordModel = mWordsPresenter.getRecentWordModelList();
        List<DictionaryModel> recentDictionaryModel = mWordsPresenter.getRecentDictionaryModelList();

        if (!recentWordModel.isEmpty()){
            mWordsFragment.showWords(
                    convertWordToDTO(recentWordModel)
            );
        }
        if (!recentDictionaryModel.isEmpty()){
            mNavDrawerFragment.showDictionaryList(
                    convertDictionaryToDTO(recentDictionaryModel)
            );
        }
    }

    private List<ModelDTO<WordModel, View.OnClickListener>> convertWordToDTO(List<WordModel> source){
        List<ModelDTO<WordModel, View.OnClickListener>> result = new ArrayList<>();
        for (final WordModel wordModel : source) {
            result.add(new ModelDTO<WordModel, View.OnClickListener>(
                    wordModel,
                    new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startWordDetailActivity(wordModel);
                }
            }));
        }
        return result;
    }

    private List<ModelDTO<DictionaryModel, View.OnClickListener>> convertDictionaryToDTO(List<DictionaryModel> source){
        List<ModelDTO<DictionaryModel, View.OnClickListener>> result = new ArrayList<>();
        for (final DictionaryModel model : source) {
            result.add(new ModelDTO<DictionaryModel, View.OnClickListener>(
                    model,
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mWordsPresenter.getWordsByDictionary(model.getId(), new DataSource.GetListCallback<WordModel>() {
                                @Override
                                public void onLoaded(List<WordModel> entries) {
                                    mWordsFragment.showWords(convertWordToDTO(entries));
                                }

                                @Override
                                public void onDataNotAvailable() {
                                    mWordsFragment.showNoWords();
                                }
                            });
                            mDrawerLayout.closeDrawers();
                        }
                    }));
        }
        return result;
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
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    public void startAddWordActivity() {
        Intent intent = new Intent(this, AddWordActivity.class);
        startActivity(intent);
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
        setupDrawerLayout();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // for single entry point
                mWordsPresenter.startAddNewWordActivity();
            }
        });
    }
}
