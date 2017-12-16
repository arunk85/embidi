package com.mm.n_deb;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class QuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        displayQuestion(false);
    }

    public void displayQuestion(boolean forwardDir){
        LoadBatch lb = new LoadBatch();
        lb.execute(forwardDir);

    }

    public void onNextClick(View view){
        displayQuestion(true);
    }

    public void onPrevClick(View view){
        displayQuestion(false);
    }

    private class LoadBatch extends AsyncTask<Boolean, Void, DBQuestion> {

        private ProgressBar pb;

        @Override
        protected void onPreExecute() {
            pb = new ProgressBar(QuestionActivity.this);

        }

        @Override
        protected DBQuestion doInBackground(Boolean... forwardDir) {
            final DBQuestion q = QuestionBatch.getQuestion(QuestionActivity.this, "tbatch", forwardDir[0]);
            return q;
        }

        @Override
        protected void onPostExecute(final DBQuestion question){
            TextView textView = findViewById(R.id.textView);
            textView.setText(question.getQuestion());

            final ListView listView = findViewById(R.id.listView);
            listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    QuestionActivity.this,
                    android.R.layout.simple_list_item_1,
                    question.getListOption());

            listView.setAdapter(arrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    if(question.getListOption().get(position).equals(question.getAnswer())){
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
}
