package com.mm.n_deb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showQuestions(View view){
        Intent intent = new Intent(this, QuestionActivity.class);
        DbUtil dbUtil = new DbUtil(this);
        dbUtil.loadDbFromFile("r1t100");
        startActivity(intent);
    }
}
