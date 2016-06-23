package com.goo32v2.cooldict.words;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goo32v2.cooldict.data.models.DictionaryModel;

import java.util.List;

/**
 * Created on 23-Jun-16. (c) CoolDict
 */

public class NavDrawerFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setupNavigationDrawer(List<DictionaryModel> items){
        //        CallbackHelper callback = new CallbackHelper();
//        mDictionaryRepository.get(callback);
//
//        ArrayAdapter<String> mNavigationAdapter = new ArrayAdapter<>(
//                this,
//                android.R.layout.simple_list_item_1,
//                callback.getModelStrings());
//        mNavigationLV.setAdapter(mNavigationAdapter);
//        mNavigationLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                mDrawerLayout.closeDrawers();
//                setNewFragment(parent.getItemAtPosition(position).toString());
//            }
//        });
    }

    public void markCurrentItem(boolean isMarked){

    }

    public void update(){

    }

//    class CallbackHelper implements DataSource.GetListCallback<DictionaryModel>{
//
//        private List<DictionaryModel> models;
//
//        @Override
//        public void onLoaded(List<DictionaryModel> entry) {
//            this.models = entry;
//        }
//
//        @Override
//        public void onDataNotAvailable() {
//            this.models = Collections.emptyList();
//        }
//
//        public List<DictionaryModel> getModels() {
//            return models;
//        }
//
//        public List<String> getModelStrings(){
//            List<String> res = new ArrayList<>();
//            for (DictionaryModel model : models) {
//                res.add(model.getTitle());
//            }
//            return res;
//        }
//    }
}
