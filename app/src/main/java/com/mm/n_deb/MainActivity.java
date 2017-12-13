package com.mm.n_deb;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
        LoadTask dbLoader = new LoadTask();
        DbUtil dbUtil = new DbUtil(this);
        dbLoader.execute(dbUtil);
    }
}


class LoadTask extends AsyncTask<DbUtil, Void, Context>{
    @Override
    protected Context doInBackground(DbUtil... dbUtils) {
        dbUtils[0].loadDbFromFile("r1t100");
        return dbUtils[0].getContext();
    }

    @Override
    protected void onPostExecute(Context cx) {
        Intent intent = new Intent(cx, QuestionActivity.class);
        cx.startActivity(intent);
    }
}
