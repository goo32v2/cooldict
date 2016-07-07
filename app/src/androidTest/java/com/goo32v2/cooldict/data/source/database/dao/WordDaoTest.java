package com.goo32v2.cooldict.data.source.database.dao;

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.runner.RunWith;

/**
 * Created on 28-May-16. (c) CoolDict
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class WordDaoTest {

//    private WordDao mWordDao;
//    private WordModel wordOne = new WordModel("one", "1");
//    private WordModel wordTwo = new WordModel("two", "2");
//    private WordModel wordThree = new WordModel("three", "3", "not_exist_dictionary");
//    private WordModel wordFour = new WordModel("four", "4", "not_exist_dictionary");
//
//    @Before
//    public void setup(){
//        mWordDao = WordDao.getInstance(InstrumentationRegistry.getTargetContext());
//    }
//
//    @After
//    public void cleanup(){
//        mWordDao.removeAll();
//    }
//
//
//    @Test
//    public void save_getById(){
//        mWordDao.save(wordOne);
//        mWordDao.get(wordOne.getId(), new DataSource.GetEntryCallback<WordModel>() {
//            @Override
//            public void onLoaded(WordModel entry) {
//                checkNotNull(entry);
//                assertTrue(entry.equals(wordOne));
//            }
//
//            @Override
//            public void onDataNotAvailable() {
//                fail();
//            }
//        });
//    }
//
//    @Test
//    public void getAll(){
//        mWordDao.save(wordTwo);
//        mWordDao.get(new DataSource.GetListCallback<WordModel>() {
//            @Override
//            public void onLoaded(List<WordModel> entry) {
//                assertTrue(!entry.isEmpty());
//                assertThat(entry.size(), is(1));
//                assertTrue(entry.get(0).equals(wordTwo));
//            }
//
//            @Override
//            public void onDataNotAvailable() {
//                fail();
//            }
//        });
//        mWordDao.save(wordOne);
//        mWordDao.get(new DataSource.GetListCallback<WordModel>() {
//            @Override
//            public void onLoaded(List<WordModel> entry) {
//                assertTrue(!entry.isEmpty());
//                assertThat(entry.size(), is(2));
//                assertTrue(entry.get(1).equals(wordOne));
//            }
//
//            @Override
//            public void onDataNotAvailable() {
//                fail();
//            }
//        });
//
//    }
//
//    @Test
//    public void update(){
//        mWordDao.save(wordOne);
//        mWordDao.update(wordOne.getId(), new WordModel(wordOne.getId(), "test", "тест", wordOne.getDictionaryById()));
//        mWordDao.get(wordOne.getId(), new DataSource.GetEntryCallback<WordModel>() {
//            @Override
//            public void onLoaded(WordModel entry) {
//                assertThat(entry.getId(), is(wordOne.getId()));
//                assertThat(entry.getOriginalWord(), is("test"));
//                assertThat(entry.getTranslatedWord(), is("тест"));
//                assertThat(entry.getDictionaryById(), is(wordOne.getDictionaryById()));
//            }
//
//            @Override
//            public void onDataNotAvailable() {
//                fail();
//            }
//        });
//    }
//
//    @Test
//    public void getAllByDictionary(){
//        mWordDao.save(wordThree);
//        mWordDao.getAllWordsByDictionary(wordThree.getDictionaryById(), new DataSource.GetListCallback<WordModel>() {
//            @Override
//            public void onLoaded(List<WordModel> entry) {
//                assertThat(entry.size(), is(1));
//            }
//
//            @Override
//            public void onDataNotAvailable() {
//                fail();
//            }
//        });
//
//        mWordDao.save(wordOne);
//        mWordDao.save(wordTwo);
//        mWordDao.getAllWordsByDictionary(Constants.DEFAULT_DICTIONARY_ID, new DataSource.GetListCallback<WordModel>() {
//            @Override
//            public void onLoaded(List<WordModel> entry) {
//                assertThat(entry.size(), is(2));
//            }
//
//            @Override
//            public void onDataNotAvailable() {
//                fail();
//            }
//        });
//    }
//
//    @Test
//    public void remove(){
//        mWordDao.save(wordOne);
//        mWordDao.save(wordTwo);
//
//        mWordDao.remove(wordOne);
//        mWordDao.get(new DataSource.GetListCallback<WordModel>() {
//            @Override
//            public void onLoaded(List<WordModel> entry) {
//                assertThat(entry.size(), is(1));
//            }
//
//            @Override
//            public void onDataNotAvailable() {
//                fail();
//            }
//        });
//
//        mWordDao.remove(wordTwo);
//        mWordDao.get(new DataSource.GetListCallback<WordModel>() {
//            @Override
//            public void onLoaded(List<WordModel> entry) {
//                fail();
//            }
//
//            @Override
//            public void onDataNotAvailable() {
//                // test pass
//            }
//        });
//    }
}
