package com.goo32v2.cooldict.view.activities;

//public class WordManagerActivity extends AppCompatActivity implements WordManagerViewContract {
//
//    public static final String ARGUMENT_EDIT_WORD = "EDIT_WORD";
//
//    @Inject protected WordManagerPresenter mPresenter;
//    private WordManagerFragment mFragment;
//
//    private WordModel model;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_word_manage);
//        CoolDictApp.getComponent().inject(this);
//
//        if (getSupportFragmentManager().getFragments().get(0) instanceof WordManagerFragment){
//            mFragment = (WordManagerFragment) getSupportFragmentManager().getFragments().get(0);
//        }
//
//        if (getIntent().hasExtra(ARGUMENT_EDIT_WORD)){
//            model = (WordModel) getIntent().getSerializableExtra(ARGUMENT_EDIT_WORD);
//        }
//
//        if (getIntent().hasExtra(ARGUMENT_EDIT_WORD)) {
//            setupActionBar(R.string.actionbar_title_edit_word_activity);
//        } else {
//            setupActionBar(R.string.actionbar_title_add_new_word_activity);
//        }
//
//        mFragment.setImeAction(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                boolean handled = false;
//                if (actionId == EditorInfo.IME_ACTION_SEND) {
//                    submit();
//                    handled = true;
//                }
//                return handled;
//            }
//        });
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mPresenter.onCreate(this);
//        mPresenter.start(model);
//    }
//
//    @Override
//    public void setupDictionaryAdapter(List<String> dictionaryNames) {
//        mFragment.setDictionaryAdapter(dictionaryNames);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_submit_word, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.action_submit_word) {
//            submit();
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public boolean onSupportNavigateUp() {
//        onBackPressed();
//        return true;
//    }
//
//    @Override
//    public void submit() {
//        String wordId = mFragment.getWordId();
//        String originalText = mFragment.getOriginalWord();
//        String translatedText = mFragment.getTranslatedWord();
//        String dictionary = mFragment.getDictionary();
//
//        mPresenter.formSubmit(wordId, originalText, translatedText, dictionary);
//    }
//
//    // translatedText can be empty
////        if (getIntent().hasExtra(ARGUMENT_EDIT_WORD)){
////            update(wordId, originalText, translatedText, dictionary);
////        } else {
////            create(originalText, translatedText, dictionary);
////        }
////    }
//
////    private void update(String id, String originalText, String translatedText, String dictionary){
////        if (originalText.isEmpty() || dictionary.isEmpty()){
////            mPresenter.showMessage(getString(R.string.empty_fields_error));
////        } else {
////            mPresenter.update(id, originalText, translatedText, dictionary);
////        }
////    }
////
////    private void create(String originalText, String translatedText, String dictionary){
////        if (originalText.isEmpty() || dictionary.isEmpty()){
////            mPresenter.showMessage(getString(R.string.empty_fields_error));
////        } else {
////            mPresenter.create(originalText, translatedText, dictionary);
////        }
////    }
//
//    private void setupActionBar(int stringRes){
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setTitle(stringRes);
//        }
//
//    }
//
//    @Override
//    public void showMessage(String msg) {
//        Toast.makeText(WordManagerActivity.this, msg, Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void finishActivity() {
//        this.setResult(Activity.RESULT_OK);
//        this.finish();
//    }
//
//    @Override
//    public void populateWord(WordModel model) {
//        mFragment.populate(model);
//    }
//
//    public static void startActivity(Context context, WordModel model) {
//        Intent intent = new Intent(context, WordManagerActivity.class);
//        if (model != null) {
//            intent.putExtra(ARGUMENT_EDIT_WORD, model);
//        }
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intent);
//    }
//}
