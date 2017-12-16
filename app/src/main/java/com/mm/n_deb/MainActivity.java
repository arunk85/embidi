package com.mm.n_deb;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showQuestions(View view){
        LoadTask dbLoader = new LoadTask();
        dbLoader.execute();
    }

    public class LoadTask extends AsyncTask<Void, Void, Void>{
        private DbUtil dbUtil;

        public LoadTask(){
            dbUtil = new DbUtil(MainActivity.this);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dbUtil.loadDbFromFile();
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            Intent intent = new Intent(MainActivity.this, BatchActivity.class);
            MainActivity.this.startActivity(intent);
        }
    }
}



