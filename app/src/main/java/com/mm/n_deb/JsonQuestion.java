package com.mm.n_deb;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class JsonQuestion {
    private String _id;
    private String _question;
    private List<String> _options;
    private String _answer;
    private Integer _correctAttepmts;
    private Integer _failedAttempts;
    private boolean _visited;
    private String _batch;

    public JsonQuestion(@JsonProperty("id") String id,
                    @JsonProperty("question") String question,
                    @JsonProperty("options") List<String> options,
                    @JsonProperty("answer") String answer,
                    @JsonProperty("correctAttempts") Integer correctAttempts,
                    @JsonProperty("failedAttempts") Integer failedAttempts,
                    @JsonProperty("visited") boolean visited,
                    @JsonProperty("batch") String batch) {
        _id = id;
        _question = question;
        _options = options;
        _answer = answer;
        _correctAttepmts = correctAttempts;
        _failedAttempts = failedAttempts;
        _visited = visited;
        _batch = batch;
    }


    public DBQuestion getAsDbQuestion(){
        StringBuilder sb = new StringBuilder();
        for(String o : _options){
            sb.append(o).append("#");
        }
        String optionString = "";
        if(sb.length() != 0){
            optionString = sb.substring(0, sb.length() -1);
        }
        return new DBQuestion(_id,
                _question,
                optionString,
                _answer,
                _correctAttepmts,
                _failedAttempts,
                _visited,
                _batch);
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getQuestion() {
        return _question;
    }

    public void setQuestion(String _question) {
        this._question = _question;
    }

    public List<String> getOptions() {
        return _options;
    }

    public void setOptions(List<String> _options) {
        this._options = _options;
    }

    public String getAnswer() {
        return _answer;
    }

    public void setAnswer(String _answer) {
        this._answer = _answer;
    }

    public Integer getCorrectAttepmts() {
        return _correctAttepmts;
    }

    public void setCorrectAttepmts(Integer _correctAttepmts) {
        this._correctAttepmts = _correctAttepmts;
    }

    public Integer getFailedAttempts() {
        return _failedAttempts;
    }

    public void setFailedAttempts(Integer _failedAttempts) {
        this._failedAttempts = _failedAttempts;
    }

    public boolean isVisited() {
        return _visited;
    }

    public void setVisited(boolean _visited) {
        this._visited = _visited;
    }

    public String getBatch() {
        return _batch;
    }

    public void setBatch(String _batch) {
        this._batch = _batch;
    }

}
