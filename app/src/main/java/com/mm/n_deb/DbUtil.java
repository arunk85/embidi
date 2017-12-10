package com.mm.n_deb;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;


public class DbUtil {
    private Context _context;
    private QDatabase _db;
    private final ObjectMapper mapper = new ObjectMapper();

    public DbUtil(Context cx) {
        _context = cx;
        _db = QDatabase.getDatabase(_context);
        mapper.registerModule(new Jdk8Module());
    }

    public void loadDbFromFile(String file) {
        int id = _context.getResources().getIdentifier(file,
                "raw", _context.getPackageName());
        InputStream is = _context.getResources().openRawResource(id);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        try {
            while ((line = br.readLine()) != null) {
                JsonQuestion jq = mapper.readValue(line, JsonQuestion.class);
                DBQuestion dbq = jq.getAsDbQuestion();
                _db.dbQuestionDao().addQuestion(dbq);
            }

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
