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
        final DBQuestion q = QuestionBatch.getQuestion(this, "testBatch", forwardDir);
        textView.setText(q.getQuestion());

        final ListView listView = findViewById(R.id.listView);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                q.getListOption());

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                if(q.getListOption().get(position).equals(q.getAnswer())){
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

    public void onNextClick(View view){
        displayQuestion(true);
    }

    public void onPrevClick(View view){
        displayQuestion(false);
    }
}
