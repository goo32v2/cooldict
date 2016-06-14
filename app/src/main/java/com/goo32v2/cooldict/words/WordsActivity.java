package com.goo32v2.cooldict.words;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.goo32v2.cooldict.Injection;
import com.goo32v2.cooldict.R;
import com.goo32v2.cooldict.utils.ActivityUtils;

public class WordsActivity extends AppCompatActivity {

    // these is variable for save state method (i think it is unnecessary now)
    private static final String CURRENT_DICTIONARY = "CURRENT_DICTIONARY";

    @Nullable WordsFragment wordsFragment;
    private WordsWordPresenter mWordsPresenter;
    private DrawerLayout mDrawerLayout;


    // TODO: 17-May-16 think about sorting words with rating
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null){
            setupDrawerContent(navigationView);
        }

         wordsFragment = (WordsFragment)
                getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (wordsFragment == null){
            wordsFragment = WordsFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    wordsFragment,
                    R.id.contentFrame);
        }

        mWordsPresenter = new WordsWordPresenter(Injection.provideWordRepository(
                getApplicationContext()), wordsFragment);

        if (savedInstanceState != null){
            // TODO: 17-May-16 Get serialized current dictionary from navigation drawer
            savedInstanceState.getSerializable(CURRENT_DICTIONARY);
        }
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener(){
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        switch (item.getItemId()){
                            // TODO: 17-May-16 Add dictionaries from db to navigation drawer

                            default:
                                break;
                        }

                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                }
        );
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

}
