package com.mm.n_deb;

import android.content.Context;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by arun on 23/11/17.
 */

public class QuestionBatch {
    private static Map<String, QuestionBatch> _batchCache = new HashMap<>();
    private String _range;
    private LinkedList<Question> _questions;
    private int _pos = 0;


    public QuestionBatch(String range,
                         List<Question> questions){
        _range = range;
        _questions = new LinkedList<>(questions);
    }

    private static QuestionBatch getBatchForRange(Context context, String range){
        if(_batchCache.containsKey(range)){
            return _batchCache.get(range);
        } else {
            int id = context.getResources().getIdentifier("r1t100",
                    "raw", context.getPackageName());
            InputStream is = context.getResources().openRawResource(id);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            JsonParser parser = new JsonParser();
            List<Question> questions = new ArrayList<>();
            try {
                while((line = br.readLine()) != null){
                    JsonObject elem = parser.parse(line).getAsJsonObject();
                    List<String> options = new ArrayList<>();

                    for(JsonElement jo : elem.get("options").getAsJsonArray()){
                        options.add(jo.getAsString());
                    }
                    Question q = new Question(elem.get("question").getAsString(),
                            options,
                            elem.get("answer").getAsString());
                    questions.add(q);
                }

                QuestionBatch qb = new QuestionBatch("r1t100",
                        questions);
                _batchCache.put("r1t100", qb);
                return qb;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    static Question getQuestion(Context con, String range, boolean forward){

        QuestionBatch batch = getBatchForRange(con, range);
        if(batch == null || batch._questions.size() == 0){
            return new Question("", new ArrayList<String>(), "");
        }
        if(forward){
            if(batch._pos < batch._questions.size()){
                batch._pos++;
                return batch._questions.get(batch._pos);
            } else {
                return batch._questions.get(batch._questions.size()-1);
            }
        } else {
            if(batch._pos - 1 >= 0){
                batch._pos--;
                return batch._questions.get(batch._pos);
            } else {
                return batch._questions.get(0);
            }
        }
    }
}
