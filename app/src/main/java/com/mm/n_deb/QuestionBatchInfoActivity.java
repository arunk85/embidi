package com.mm.n_deb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class QuestionBatchInfoActivity extends AppCompatActivity {

    String sBatch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_batch_info);
        Intent intent = getIntent();
        sBatch = intent.getStringExtra("selectedBatch");
    }

    public void onShowAllClick(View view){
        Intent quesIntent = new Intent(this, QuestionActivity.class);
        quesIntent.putExtra("selectedBatch", sBatch);
        startActivity(quesIntent);
    }
}
