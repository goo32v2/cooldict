package com.goo32v2.cooldict.data.source.database.dao;

import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.sources.SourcesConstants;
import com.goo32v2.cooldict.data.sources.database.dao.DictionaryDao;
import com.goo32v2.cooldict.data.sources.interfaces.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created on 23-May-16. (c) CoolDict
 */


@RunWith(AndroidJUnit4.class)
@LargeTest
public class DictionaryDaoTest {

    final private static String TITLE_1 = "Test_Dictionary_1";
    final private static String TITLE_2 = "Test_Dictionary_2";
    private DictionaryDao mDictionaryDao;

    @Before
    public void setup(){
        mDictionaryDao = DictionaryDao.getInstance(InstrumentationRegistry.getTargetContext());
    }

    @After
    public void cleanUp(){
        mDictionaryDao.removeAll();
    }

    @Test
    public void save_getById(){
        final DictionaryModel dict = new DictionaryModel(TITLE_1);

        mDictionaryDao.save(dict);
        mDictionaryDao.get(dict.getId(), new DataSource.GetEntryCallback<DictionaryModel>() {
            @Override
            public void onLoaded(DictionaryModel entry) {
                assertThat(dict, is(entry));
            }

            @Override
            public void onDataNotAvailable() {
                fail("Save failed (error callback)");
            }
        });
    }

    @Test
    public void getDefault(){

        mDictionaryDao.getDefaultDictionary(new DataSource.GetEntryCallback<DictionaryModel>() {
            @Override
            public void onLoaded(DictionaryModel entry) {
                assertThat(entry.getId(), is(SourcesConstants.DEFAULT_DICTIONARY_ID));
                assertThat(entry.getTitle(), is(SourcesConstants.DEFAULT_DICTIONARY_NAME));
            }

            @Override
            public void onDataNotAvailable() {
                fail();
            }
        });
    }

    @Test
    public void getAll(){
        // TODO: 28-May-16 fixed hardcode value
        mDictionaryDao.get(new DataSource.GetListCallback<DictionaryModel>() {
            @Override
            public void onLoaded(List<DictionaryModel> entry) {
                assertNotNull(entry);
                assertThat(entry.size(), is(1));
            }

            @Override
            public void onDataNotAvailable() {
                fail("no data available");
            }
        });

        mDictionaryDao.save(new DictionaryModel(TITLE_2));

        mDictionaryDao.get(new DataSource.GetListCallback<DictionaryModel>() {
            @Override
            public void onLoaded(List<DictionaryModel> entry) {
                assertNotNull(entry);
                assertThat(entry.size(), is(2));
            }

            @Override
            public void onDataNotAvailable() {
                fail("no data available");
            }
        });
    }

    @Test
    public void remove(){
        DictionaryModel removeByIdModel = new DictionaryModel(TITLE_1);
        mDictionaryDao.save(removeByIdModel);

        DictionaryModel removeByObjModel = new DictionaryModel(TITLE_1);
        mDictionaryDao.save(removeByObjModel);

        mDictionaryDao.remove(removeByIdModel.getId());
        mDictionaryDao.remove(removeByObjModel);
    }

    @Test
    public void update(){
        final DictionaryModel oldModel = new DictionaryModel(TITLE_1);
        mDictionaryDao.save(oldModel);
        DictionaryModel newModel = new DictionaryModel(oldModel.getId(), TITLE_2);
        mDictionaryDao.update(oldModel.getId(), newModel);

        mDictionaryDao.get(oldModel.getId(), new DataSource.GetEntryCallback<DictionaryModel>() {
            @Override
            public void onLoaded(DictionaryModel entry) {
                assertThat(entry.getTitle(), is(TITLE_2));
            }

            @Override
            public void onDataNotAvailable() {
                fail();
            }
        });
    }

}
