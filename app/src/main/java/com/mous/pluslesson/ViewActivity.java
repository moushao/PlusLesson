package com.mous.pluslesson;
import	java.util.regex.Matcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mous.pluslesson.lesson9.MaterialEditText;

public class ViewActivity extends AppCompatActivity {
    MaterialEditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        edit = findViewById(R.id.editText);
        edit.postDelayed(new Runnable() {
            @Override
            public void run() {
                edit.setUseFloatingLabel(false);
            }
        }, 3000);
    }
}
