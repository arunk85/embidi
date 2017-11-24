package com.mm.n_deb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class QuestionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        displayQuestion(false);
    }

    public void displayQuestion(boolean forwardDir){
        TextView textView = findViewById(R.id.textView);
        final Question q = QuestionBatch.getQuestion(this, "r1t100", forwardDir);
        textView.setText(q.getQuestion());

        final ListView listView = findViewById(R.id.listView);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                q.getOptions());

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                if(q.getOptions().get(position).equals(q.getAnswer())){
                    Toast.makeText(QuestionActivity.this,
                            "Correct",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(QuestionActivity.this,
                        "Wrong",
                        Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}