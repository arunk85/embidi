package com.mm.n_deb;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;


public class DbUtil {
    private Context _context;
    private QDatabase _db;

    public DbUtil(Context cx) {
        _context = cx;
        _db = QDatabase.getDatabase(_context);
    }

    public void loadDbFromFile() {
        int rowCount = _db.dbQuestionDao().getCount();
        if(rowCount != 0) return ;
        String dbPath = _context.getDatabasePath("questionsdatabase").toString();

        try {
            InputStream myInput = _context.getAssets().open("questionsdatabase");
            OutputStream myOutput = new FileOutputStream(dbPath);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<DBQuestion> getQuestionsForBatch(String batchId) {
        return _db.dbQuestionDao().getQuestions(batchId);
    }

    public List<String> getBatchNames(){
        return _db.dbQuestionDao().getBatches();
    }

}
