package com.example.rxjava.ui.catalog;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rxjava.MainViewModel;
import com.example.rxjava.databinding.FragmentCatalogBinding;

public class CatalogFragment extends Fragment {

    private FragmentCatalogBinding mBinding;
    private MainViewModel mViewModel;
    private CatalogAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentCatalogBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(getActivity(), new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);
        mAdapter = new CatalogAdapter();
        mBinding.catalogRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.catalogRecyclerView.setAdapter(mAdapter);
        mViewModel.getNovel().observe(getViewLifecycleOwner(), novel -> {
            mAdapter.setCatalog(novel.getCatalog());
        });
        return mBinding.getRoot();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mBinding = null;
        mViewModel = null;
    }
}