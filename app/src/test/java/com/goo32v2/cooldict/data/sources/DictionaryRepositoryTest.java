package com.goo32v2.cooldict.data.sources;

/**
 * Created on 28-May-16. (c) CoolDict
 */

public class DictionaryRepositoryTest {

//    private DictionaryRepository mDictionaryRepository;
//
//    private List<DictionaryModel> modelList = Lists.newArrayList(new DictionaryModel("Dict1"),
//            new DictionaryModel("Dict2"));
//
//    private String dictName1 = "TestDict1";
//
//    @Mock
//    private DictionaryDao mDictionaryDao;
//
//    @Mock
//    private Cache<DictionaryModel> mDictionaryCache;
//
//    @Mock
//    private Context context;
//
//    @Mock
//    private DataSource.GetEntryCallback<DictionaryModel> mEntryGetCallback;
//
//    @Mock
//    private DataSource.GetListCallback<DictionaryModel> mListGetCallback;
//
//    @Captor
//    private ArgumentCaptor<DataSource.GetEntryCallback<DictionaryModel>> mEntryGetCaptor;
//
//    @Captor
//    private ArgumentCaptor<DataSource.GetListCallback<DictionaryModel>> mListGetCaptor;
//
//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        mDictionaryRepository = DictionaryRepository.getInstance(mDictionaryDao, mDictionaryCache);
//    }
//
//    @After
//    public void kill() {
//        DictionaryRepository.destroyInstance();
//    }
//
//    @Test
//    public void getDictionaries_onDataNotAvailable() {
//        mDictionaryRepository.get(mListGetCallback);
//        verify(mDictionaryDao).get(mListGetCaptor.capture());
//
//        mListGetCaptor.getValue().onDataNotAvailable();
//    }
//
//    @Test
//    public void save(){
//        DictionaryModel model = modelList.get(0);
//        saveEntry(model);
//        verify(mDictionaryDao).save(model);
//    }
//
//    @Test
//    public void getEntryFromCache(){
//        DictionaryModel model = modelList.get(0);
//        saveEntry(model);
//
//        mDictionaryRepository.get(model.getId(), mEntryGetCallback);
//        verify(mDictionaryCache).get(eq(model.getId()));
//        mEntryGetCallback.onLoaded(eq(model));
//    }
//
//    @Test
//    public void getEntryFromDatabase(){
//        DictionaryModel model = modelList.get(0);
//        saveEntry(model);
//        mDictionaryCache.invalidate();
//        mDictionaryRepository.get(model.getId(), mEntryGetCallback);
//        verify(mDictionaryDao).get(eq(model.getId()), mEntryGetCaptor.capture());
//        mEntryGetCallback.onLoaded(eq(model));
//    }
//
//    @Test
//    public void getListFromCache(){
//        saveList(modelList);
//
//        mDictionaryRepository.get(mListGetCallback);
//        verify(mDictionaryCache).getAll();
//        mListGetCallback.onLoaded(eq(modelList));
//    }
//
//    @Test
//    public void getListFromDatabase(){
//        saveList(modelList);
//
//        mDictionaryCache.invalidate();
//        mDictionaryRepository.get(mListGetCallback);
//        verify(mDictionaryDao).get(mListGetCaptor.capture());
//        mListGetCallback.onLoaded(eq(modelList));
//    }
//
//    @Test
//    public void update() {
//        DictionaryModel oldModel = modelList.get(0);
//        DictionaryModel newModel = new DictionaryModel(oldModel.getId(), dictName1);
//        saveEntry(oldModel);
//
//        mDictionaryRepository.update(oldModel.getId(), newModel);
//        verify(mDictionaryDao).update(eq(oldModel.getId()), eq(newModel));
//    }
//
//    @Test
//    public void remove(){
//        saveList(modelList);
//        DictionaryModel dictionaryModel = modelList.get(0);
//        mDictionaryRepository.remove(dictionaryModel.getId());
//        verify(mDictionaryDao).remove(eq(dictionaryModel.getId()));
//    }
//
//    private void saveEntry(DictionaryModel model){
//        mDictionaryRepository.save(model);
//    }
//
//    private void saveList(List<DictionaryModel> dictionaryModels){
//        for (DictionaryModel model : dictionaryModels) {
//            mDictionaryRepository.save(model);
//        }
//    }
}
