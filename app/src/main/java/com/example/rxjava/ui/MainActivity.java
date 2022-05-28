package com.example.rxjava.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;

import com.example.rxjava.R;
import com.example.rxjava.logic.network.ConstantUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainViewModel viewModel = new MainViewModelFactory(ConstantUtils.NOVEL_URL).create(MainViewModel.class);
        viewModel.setChapter(ConstantUtils.CHAPTER_URL);
        viewModel.getContent().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.e(TAG, "onChanged: " + s);
            }
        });
    }
}