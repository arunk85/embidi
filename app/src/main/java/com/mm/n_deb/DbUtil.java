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
//        /data/user/0/com.mm.n_deb/databases/questiondatabase
        _context = cx;
        _db = QDatabase.getDatabase(_context);
    }

    public Context getContext(){
        return _context;
    }

    public void loadDbFromFile() {
        String dbPath = _context.getDatabasePath("questionsdatabase").toString();

        try {
            InputStream myInput = _context.getAssets().open("questionsdatabase");
            //Open the empty dbPath as the output stream
            OutputStream myOutput = new FileOutputStream(dbPath);

            //transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }

            //Close the streams
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

    public void updateQuestionsToDb(List<DBQuestion> questions) {
        for(DBQuestion dbq : questions){
            _db.dbQuestionDao().addQuestion(dbq);
        }
    }

}
