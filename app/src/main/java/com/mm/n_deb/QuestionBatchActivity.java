package com.mm.n_deb;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

public class QuestionBatchActivity extends AppCompatActivity {

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_batch);
        gridView = findViewById(R.id.qb_act_id);

        BatchAdapter bAdapt = new BatchAdapter(this, DbUtil.batchNames);
        gridView.setAdapter(bAdapt);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                CharSequence txt = ((TextView)(((ConstraintLayout)v).getViewById(R.id.textView2))).getText();
                Intent intent = new Intent(QuestionBatchActivity.this, QuestionBatchInfoActivity.class);
                intent.putExtra("selectedBatch", txt);
                startActivity(intent);

//                Toast.makeText(getApplicationContext(),
//                        ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
