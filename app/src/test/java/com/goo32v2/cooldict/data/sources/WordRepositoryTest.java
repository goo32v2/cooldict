package com.goo32v2.cooldict.data.sources;

/**
 * Created on 28-May-16. (c) CoolDict
 */

public class WordRepositoryTest {
//    private WordRepository wordRepository;
//
//    private List<WordModel> modelList = Lists.newArrayList(new WordModel("Word1", "Слово1"),
//            new WordModel("Word2", "Слово1"));
//
//    @Mock
//    private WordDao wordDao;
//
//    @Mock
//    private Cache<WordModel> wordModelCache;
//
//    @Mock
//    private Context context;
//
//    @Mock
//    private DataSource.GetEntryCallback<WordModel> mEntryGetCallback;
//
//    @Mock
//    private DataSource.GetListCallback<WordModel> mListGetCallback;
//
//    @Captor
//    private ArgumentCaptor<DataSource.GetEntryCallback<WordModel>> mEntryGetCaptor;
//
//    @Captor
//    private ArgumentCaptor<DataSource.GetListCallback<WordModel>> mListGetCaptor;
//
//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        wordRepository = WordRepository.getInstance(wordDao, wordModelCache);
//    }
//
//    @After
//    public void kill() {
//        WordRepository.destroyInstance();
//    }
//
//    @Test
//    public void getWords_onDataNotAvailable() {
//        wordRepository.get(mListGetCallback);
//        verify(wordDao).get(mListGetCaptor.capture());
//
//        mListGetCaptor.getValue().onDataNotAvailable();
//    }
//
//    @Test
//    public void save(){
//        WordModel model = modelList.get(0);
//        saveEntry(model);
//        verify(wordDao).save(model);
//    }
//
//    @Test
//    public void getEntryFromCache1(){
//        WordModel model = modelList.get(0);
//        saveEntry(model);
//
//        wordRepository.get(model.getId(), mEntryGetCallback);
//        verify(wordModelCache).get(eq(model.getId()));
//        mEntryGetCallback.onLoaded(eq(model));
//    }
//
//    @Test
//    public void getEntryFromDatabase(){
//        WordModel model = modelList.get(0);
//        saveEntry(model);
//        wordModelCache.invalidate();
//        wordRepository.get(model.getId(), mEntryGetCallback);
//        verify(wordDao).get(eq(model.getId()), mEntryGetCaptor.capture());
//        mEntryGetCallback.onLoaded(eq(model));
//    }
//
//    @Test
//    public void getListFromCache(){
//        saveList(modelList);
//
//        wordRepository.get(mListGetCallback);
//        verify(wordModelCache).getAll();
//        mListGetCallback.onLoaded(eq(modelList));
//    }
//
//    @Test
//    public void getListFromDatabase(){
//        saveList(modelList);
//
//        wordModelCache.invalidate();
//        wordRepository.get(mListGetCallback);
//        verify(wordDao).get(mListGetCaptor.capture());
//        mListGetCallback.onLoaded(eq(modelList));
//    }
//
//    @Test
//    public void update() {
//        WordModel oldModel = modelList.get(0);
//        WordModel newModel = new WordModel(oldModel.getId(), "TestWord1",
//                "ТестСлово1", oldModel.getDictionaryById());
//        saveEntry(oldModel);
//
//        wordRepository.update(oldModel.getId(), newModel);
//        verify(wordDao).update(eq(oldModel.getId()), eq(newModel));
//    }
//
//    @Test
//    public void remove(){
//        saveList(modelList);
//        WordModel WordModel = modelList.get(0);
//        wordRepository.remove(WordModel.getId());
//        verify(wordDao).remove(eq(WordModel.getId()));
//    }
//
//    private void saveEntry(WordModel model){
//        wordRepository.save(model);
//    }
//
//    private void saveList(List<WordModel> WordModels){
//        for (WordModel model : WordModels) {
//            wordRepository.save(model);
//        }
//    }
}
