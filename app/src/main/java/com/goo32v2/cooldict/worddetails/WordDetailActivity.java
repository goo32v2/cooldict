package com.goo32v2.cooldict.worddetails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.goo32v2.cooldict.R;

public class WordDetailActivity extends AppCompatActivity {

    public static final String EXTRA_WORD_ID = "word_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_detail);
    }
}
