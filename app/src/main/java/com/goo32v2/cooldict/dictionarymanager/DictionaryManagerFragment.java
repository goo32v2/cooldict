package com.goo32v2.cooldict.dictionarymanager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goo32v2.cooldict.R;
import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.models.ModelDTO;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 23-Jun-16. (c) CoolDict
 */

public class DictionaryManagerFragment extends Fragment {

    @BindView(R.id.dictionary_recycler_view) RecyclerView mRecycleView;
    private DictionaryManagerRecycleAdapter mAdapter;
    private LinearLayoutManager llm;

    // empty
    public DictionaryManagerFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    //call it from activity
    public void showDictionaryList(List<ModelDTO<DictionaryModel, View.OnClickListener>> list) {
        if (llm == null || mAdapter == null) {
            setupRecycleView(list);
        } else {
            update(list);
        }
    }

    private void setupRecycleView(List<ModelDTO<DictionaryModel, View.OnClickListener>> dictionaryModelList) {
        llm = new LinearLayoutManager(getActivity());
        mRecycleView.setLayoutManager(llm);
        mAdapter = new DictionaryManagerRecycleAdapter(dictionaryModelList);
        mRecycleView.setAdapter(mAdapter);
    }

    public void markCurrentItem(boolean isMarked) {
        // TODO: 25-Jun-16 implement
    }

    public void update(List<ModelDTO<DictionaryModel, View.OnClickListener>> dictionaryModelList) {
        mAdapter.replaceData(dictionaryModelList);
        mAdapter.notifyDataSetChanged();
    }
}