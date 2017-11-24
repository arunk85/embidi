package com.mm.n_deb;

import java.util.List;

/**
 * Created by arun on 22/11/17.
 */

public class Question {
    private final String _question;
    private final List<String> _options;
    private final String _answer;
    private int _totalAttempts;
    private int _correctAttempts;

    public Question(String q,
                    List<String> opts,
                    String ans){
        _question = q;
        _options = opts;
        _answer = ans;
    }

    public String getQuestion(){
        return _question;
    }

    public String getAnswer() {
        return _answer;
    }

    public List<String> getOptions() {
        return _options;
    }

    public void attempted(boolean success){
        _totalAttempts++;
        _correctAttempts++;
    }

    public int getTotalAttempts(){
        return _totalAttempts;
    }

    public int getCorrectAttempts(){
        return _correctAttempts;
    }

    public boolean isCorrectAnswer(String given) {
        boolean res = _answer.equals(given);
        if(res){
            _correctAttempts++;
        }
        _totalAttempts++;
        return res;
    }
}
