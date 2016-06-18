package com.goo32v2.cooldict.words;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import android.widget.Toast;

import com.goo32v2.cooldict.Injection;
import com.goo32v2.cooldict.R;
import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.sources.DictionaryRepository;
import com.goo32v2.cooldict.data.sources.interfaces.DataSource;
import com.goo32v2.cooldict.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WordsActivity extends AppCompatActivity {

    private static final String CURRENT_DICTIONARY = "CURRENT_DICTIONARY";

    @Nullable
    private WordsFragment wordsFragment;
    private DrawerLayout mDrawerLayout;
    private ListView mNavigationLV;
    private WordsPresenter mWordsPresenter;
    private ArrayAdapter<String> mNavigationAdapter;
    private DictionaryRepository mDictionaryRepository;


    // TODO: 17-May-16 think about sorting words with rating
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        mDictionaryRepository = Injection.provideDictionaryRepository(getApplicationContext());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationLV = (ListView) findViewById(R.id.navList);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        if (navigationView != null){
//            setupDrawerContent(navigationView);
//        }
        setupDrawerContent(mNavigationLV);

         wordsFragment = (WordsFragment)
                getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (wordsFragment == null){
            wordsFragment = WordsFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    wordsFragment,
                    R.id.contentFrame);
        }

        mWordsPresenter = new WordsPresenter(Injection.provideWordRepository(
                getApplicationContext()), wordsFragment);

        if (savedInstanceState != null){
            // TODO: 17-May-16 Get serialized current dictionary from navigation drawer
            savedInstanceState.getSerializable(CURRENT_DICTIONARY);
        }
    }

    private void setupDrawerContent(ListView navLV) {
        CallbackHelper callback = new CallbackHelper();
        mDictionaryRepository.get(callback);
        mNavigationAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                callback.getModelStrings());
        navLV.setAdapter(mNavigationAdapter);
        navLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(WordsActivity.this, parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO: 17-May-16 Save state (dictionary? or implement some kind of sorting?)
//        outState.putSerializable(CURRENT_DICTIONARY, mWordsPresenter.getCurrentDictionary());

        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.words, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
