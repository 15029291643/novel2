package com.example.rxjava.ui.chapter;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;

import com.example.rxjava.databinding.FragmentChapterBinding;
import com.example.rxjava.logic.network.ConstantUtils;
import com.example.rxjava.ui.main.MainViewModel;

public class ChapterFragment extends Fragment {

    private MainViewModel mViewModel;
    private FragmentChapterBinding mBinding;
    private static final String TAG = "ChapterFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentChapterBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(getActivity(),
                new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);
        int position = getArguments().getInt("position");
        Log.e(TAG, "onCreateView: " + position);
        mViewModel.setChapter(position);
        mViewModel.getTitle().observe(getViewLifecycleOwner(), s ->
                mBinding.chapterTitle.setText(s));
        mViewModel.getContent().observe(getViewLifecycleOwner(), s ->
                mBinding.chapterContent.setText(s));
        return mBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewModel = null;
    }
}