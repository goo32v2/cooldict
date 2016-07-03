package com.goo32v2.cooldict.view.activities;

/**
 * Created on 29-Jun-16. (c) CoolDict
 */

public class DictionaryManagerActivity {
//public class DictionaryManagerActivity extends AppCompatActivity implements DictionaryManagerViewContract {

//    private DictionaryManagerPresenterContract mPresenter;
//    private DictionaryManagerFragment mFragment;
//
//    @Inject
//    protected DictionaryRepository dictionaryRepository;
//
//    @Inject
//    protected WordRepository wordRepository;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        CoolDictApp.getComponent().inject(this);
//
//        new DictionaryManagerPresenter(
//                dictionaryRepository,
//                wordRepository,
//                this
//        );
//
//        setContentView(R.layout.activity_dictionary_manager);
//
//        List<Fragment> fragments = getSupportFragmentManager().getFragments();
//        if (!fragments.isEmpty() && fragments.get(0) instanceof DictionaryManagerFragment){
//            mFragment = (DictionaryManagerFragment) fragments.get(0);
//        }
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mPresenter.start();
//
//        mPresenter.get(new DataSource.GetListCallback<DictionaryModel>() {
//            @Override
//            public void onLoaded(List<DictionaryModel> entries) {
//                mFragment.showDictionaryList(
//                        convertDictionaryToDTO(entries)
//                );
//            }
//
//            @Override
//            public void onDataNotAvailable() {
//                mFragment.showDictionaryList(
//                        convertDictionaryToDTO(new ArrayList<DictionaryModel>())
//                );
//            }
//        });
//    }
//
//    private List<ModelDTO<DictionaryModel, View.OnClickListener>> convertDictionaryToDTO(List<DictionaryModel> source){
//        List<ModelDTO<DictionaryModel, View.OnClickListener>> result = new ArrayList<>();
//        for (final DictionaryModel model : source) {
//            result.add(new ModelDTO<DictionaryModel, View.OnClickListener>(
//                    model,
//                    new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            askConfirmation(model).show();
//                        }
//                    }));
//        }
//        return result;
//    }
//
//    private AlertDialog askConfirmation(final DictionaryModel model){
//        return new AlertDialog.Builder(this)
//                .setTitle(getString(R.string.title_delete_confirmation_dialog))
//                .setMessage(getString(R.string.message_delete_confirmation_dialog))
//                .setPositiveButton(getString(R.string.positive_delete_confirmation_dialog), new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        mPresenter.remove(model);
//                        onResume();
//                    }
//                })
//                .setNegativeButton(getString(R.string.negative_delete_confirmation_dialog), new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                })
//                .create();
//    }
//
//    @Override
//    public boolean onSupportNavigateUp() {
//        onBackPressed();
//        return true;
//    }
//
//    @Override
//    public void setPresenter(DictionaryManagerPresenter presenter) {
//        mPresenter = presenter;
//    }
}
