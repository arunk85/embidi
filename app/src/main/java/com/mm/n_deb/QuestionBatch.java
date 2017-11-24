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
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by arun on 23/11/17.
 */

public class QuestionBatch {
    private static Map<String, QuestionBatch> _batchCache = new HashMap<>();
    public String _range;
    public LinkedList<Question> _questionsF;
    public LinkedList<Question> _questionsB;


    public QuestionBatch(String range,
                         List<Question> questions){
        _range = range;
        _questionsF = new LinkedList<>(questions);
        _questionsB = new LinkedList<>();
    }

    private static QuestionBatch getBatchForRange(Context context, String range){
        if(_batchCache.containsKey(range)){
            return _batchCache.get(range);
        } else {
            InputStream is = context.getResources().openRawResource(R.raw.r1t100);
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

    public static Question getQuestion(Context con, String range, boolean forward){

        QuestionBatch batch = getBatchForRange(con, range);
        if(forward){
            if(batch._questionsF.isEmpty()){
                LinkedList<Question> tmp = batch._questionsF;
                batch._questionsF = batch._questionsB;
                batch._questionsB = tmp;
            }
            Question q = batch._questionsF.peekFirst();
            batch._questionsB.addLast(batch._questionsF.pollFirst());
            return q;
        } else {
            if(batch._questionsB.isEmpty()){
                return batch._questionsF.peekFirst();
            } else {
                Question q = batch._questionsB.peekLast();
                batch._questionsF.addFirst(batch._questionsB.pollLast());
                return q;
            }
        }
    }
}
