package com.goo32v2.cooldict.words;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.goo32v2.cooldict.Injection;
import com.goo32v2.cooldict.R;
import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.sources.DictionaryRepository;
import com.goo32v2.cooldict.data.sources.interfaces.DataSource;
import com.goo32v2.cooldict.settings.SettingsActivity;
import com.goo32v2.cooldict.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WordsActivity extends AppCompatActivity {

    private static final String CURRENT_DICTIONARY = "CURRENT_DICTIONARY";

    private static boolean isAnyFragmentSetup = false;
    private DrawerLayout mDrawerLayout;
    private ListView mNavigationLV;
    private DictionaryRepository mDictionaryRepository;


    // TODO: 17-May-16 think about sorting words with rating
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDictionaryRepository = Injection.provideDictionaryRepository(getApplicationContext());

        setupView();
        setupDrawerContent();
        setNewFragment(null);

        if (savedInstanceState != null){
            // TODO: 17-May-16 Get serialized current dictionary from navigation drawer
            savedInstanceState.getSerializable(CURRENT_DICTIONARY);
        }
    }

    private void setupView(){
        setContentView(R.layout.activity_words);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationLV = (ListView) findViewById(R.id.navList);
        setupDrawer(toolbar);
    }

    private void setupDrawer(Toolbar toolbar){
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void setupDrawerContent() {

        CallbackHelper callback = new CallbackHelper();
        mDictionaryRepository.get(callback);
        ArrayAdapter<String> mNavigationAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                callback.getModelStrings());
        mNavigationLV.setAdapter(mNavigationAdapter);
        mNavigationLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDrawerLayout.closeDrawers();
                setNewFragment(parent.getItemAtPosition(position).toString());
            }
        });
    }

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

        new WordsPresenter(
                Injection.provideWordRepository(getApplicationContext()),
                Injection.provideDictionaryRepository(getApplicationContext()),
                fragment
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupDrawerContent();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO: 17-May-16 Save state (dictionary? or implement some kind of sorting?)
//        outState.putSerializable(CURRENT_DICTIONARY, mWordsPresenter.getCurrentDictionary());

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
            showSettings();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showSettings() {
//        Intent intent = new Intent(this, SettingsActivity.class);
//        startActivityForResult(intent, SettingsActivity.REQUEST_SETTINGS);
    }

    class CallbackHelper implements DataSource.GetListCallback<DictionaryModel>{

        private List<DictionaryModel> models;

        @Override
        public void onLoaded(List<DictionaryModel> entry) {
            this.models = entry;
        }

        @Override
        public void onDataNotAvailable() {
            this.models = Collections.emptyList();
        }

        public List<DictionaryModel> getModels() {
            return models;
        }

        public List<String> getModelStrings(){
            List<String> res = new ArrayList<>();
            for (DictionaryModel model : models) {
                res.add(model.getTitle());
            }
            return res;
        }
    }

}
