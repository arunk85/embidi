package com.mm.n_deb;

import android.content.Context;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Class representing a set of questions. Also holds a cache for the batch.
 */

class QuestionBatch {
    private static Map<String, QuestionBatch> _batchCache = new HashMap<>();
    private LinkedList<DBQuestion> _questions;
    private int _pos = 0;


    private QuestionBatch(List<DBQuestion> questions) {
        _questions = new LinkedList<>(questions);
    }


    private static QuestionBatch getBatchForRange(Context context, String range) {
        DbUtil dbUtil = new DbUtil(context);
        if (_batchCache.containsKey(range)) {
            return _batchCache.get(range);
        } else {
            List<DBQuestion> dbQuestions = dbUtil.getQuestionsForBatch(range);
            QuestionBatch qb = new QuestionBatch(dbQuestions);
            _batchCache.put(range, qb);
            return qb;
        }
    }


    static DBQuestion getQuestion(Context con, String range, boolean forward) {

        QuestionBatch batch = getBatchForRange(con, range);
        if (batch == null || batch._questions.size() == 0) {
            return null;
        }
        if (forward) {
            if (batch._pos < batch._questions.size() - 1) {
                batch._pos++;
                return batch._questions.get(batch._pos);
            } else {
                return batch._questions.get(batch._questions.size() - 1);
            }
        } else {
            if (batch._pos - 1 >= 0) {
                batch._pos--;
                return batch._questions.get(batch._pos);
            } else {
                return batch._questions.get(0);
            }
        }
    }
}
