package com.goo32v2.cooldict.view.activities;

import android.content.Intent;
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
import com.goo32v2.cooldict.data.DataSource;
import com.goo32v2.cooldict.data.dtos.ModelDTO;
import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.data.repositories.DictionaryRepository;
import com.goo32v2.cooldict.data.repositories.WordRepository;
import com.goo32v2.cooldict.presenter.WordListPresenterContract;
import com.goo32v2.cooldict.presenter.impl.WordListPresenter;
import com.goo32v2.cooldict.view.WordListViewContract;
import com.goo32v2.cooldict.view.fragments.WordListFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WordListActivity extends AppCompatActivity implements WordListViewContract,
        NavigationView.OnNavigationItemSelectedListener {

    private WordListPresenter mWordListPresenter;
    private WordListFragment mWordListFragment;

    @BindView(R.id.nav_view) NavigationView mNavigationView;
    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.fab) FloatingActionButton floatingActionButton;

    private DictionaryModel activeDictionary;
    private List<WordModel> activeWordList;

    @Inject
    protected DictionaryRepository dictionaryRepository;

    @Inject
    protected WordRepository wordRepository;

    private static final String CURRENT_DICTIONARY = "CURRENT_DICTIONARY";


    // TODO: 17-May-16 think about sorting words with rating
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        CoolDictApp.getComponent().inject(this);
        ButterKnife.bind(this);

        new WordListPresenter(
                wordRepository,
                dictionaryRepository,
                this
        );

        // get all created fragments and assign it to vars
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            if (fragment instanceof WordListFragment) {
                this.mWordListFragment = (WordListFragment) fragment;
            }
        }

        setupView();

        if (savedInstanceState != null) {
            // TODO: 17-May-16 Get serialized current dictionary from navigation drawer
            savedInstanceState.getSerializable(CURRENT_DICTIONARY);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        mWordListPresenter.getWords(new DataSource.GetListCallback<WordModel>() {
            @Override
            public void onLoaded(List<WordModel> entries) {
                mWordListFragment.showWords(
                        convertWordToDTO(entries)
                );
            }

            @Override
            public void onDataNotAvailable() {
                mWordListFragment.showWords(
                        convertWordToDTO(new ArrayList<WordModel>())
                );
            }
        });

        mWordListPresenter.getDictionaries(new DataSource.GetListCallback<DictionaryModel>() {
            @Override
            public void onLoaded(List<DictionaryModel> entries) {
                setMenu(entries);
            }

            @Override
            public void onDataNotAvailable() {
                setMenu(new ArrayList<DictionaryModel>());
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mNavigationView.getMenu().clear();
    }

    private List<ModelDTO<WordModel, View.OnClickListener>> convertWordToDTO(List<WordModel> source) {
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO: 17-May-16 Save state (dictionary? or implement some kind of sorting?)
//        outState.putSerializable(CURRENT_DICTIONARY, mWordListPresenter.getAllDictionaries());

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
            mWordListPresenter.startSettingsActivity();
            return true;
        }
        if (id == R.id.action_dictionary_manager) {
            mWordListPresenter.startDictionaryManagerActivity();
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
        Intent intent = new Intent(this, WordManagerActivity.class);
        startActivity(intent);
    }

    @Override
    public void startWordDetailActivity(WordModel word) {
        Intent intent = new Intent(this, WordDetailActivity.class);
        intent.putExtra(WordDetailActivity.EXTRA_WORD_ID, word);
        startActivity(intent);
    }

    @Override
    public void startDictionaryManagerActivity() {
        Intent intent = new Intent(this, DictionaryManagerActivity.class);
        startActivity(intent);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(WordListActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(WordListPresenterContract presenter) {
        this.mWordListPresenter = (WordListPresenter) presenter;
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
                mWordListPresenter.startAddNewWordActivity();
            }
        });
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    private void setMenu(List<DictionaryModel> entries) {
        Menu m = mNavigationView.getMenu();
        m.add(R.string.show_all_words);
        for (DictionaryModel item : entries) {
            m.add(item.getTitle());
        }
    }


    public DictionaryModel getActiveDictionary() {
        return activeDictionary;
    }

    public void setActiveDictionary(DictionaryModel activeDictionary) {
        this.activeDictionary = activeDictionary;
    }

    public List<WordModel> getActiveWordList() {
        return activeWordList;
    }

    public void setActiveWordList(List<WordModel> activeWordList) {
        this.activeWordList = activeWordList;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        setTitle(item.getTitle());
        if (Objects.equals(item.getTitle().toString(), getString(R.string.show_all_words))) {
            mWordListPresenter.getWords(new DataSource.GetListCallback<WordModel>() {
                @Override
                public void onLoaded(List<WordModel> entries) {
                    setActiveWordList(entries);
                }

                @Override
                public void onDataNotAvailable() {
                    setActiveWordList(null);
                }
            });
        } else {
            mWordListPresenter.getWordsByDictionaryName(item.getTitle().toString(), new DataSource.GetListCallback<WordModel>() {
                @Override
                public void onLoaded(List<WordModel> entries) {
                    setActiveWordList(entries);
//                setActiveDictionary();
//                Get dictionary model
                }

                @Override
                public void onDataNotAvailable() {
                    setActiveWordList(null);
                }
            });
        }

        if (getActiveWordList().isEmpty()) {
            mWordListFragment.showNoWords();
        } else {
            mWordListFragment.showWords(convertWordToDTO(getActiveWordList()));
        }
        mDrawerLayout.closeDrawers();
        return true;
    }
}
