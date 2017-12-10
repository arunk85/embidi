package com.mm.n_deb;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by arun on 26/11/17.
 */

@Entity(tableName = "dbquestion")
public class DBQuestion {
    @PrimaryKey
    @NonNull
    private String id;
    private String question;
    private String options;
    private String answer;
    private Integer correctAttempts;
    private Integer failedAttempts;
    private boolean visited;
    private String batch;

    @Ignore
    private List<String> listOptions;

    public DBQuestion(String id,
                      String question,
                      String options,
                      String answer,
                      Integer correctAttempts,
                      Integer failedAttempts,
                      boolean visited,
                      String batch){
        this.id = id;
        this.question = question;
        this.options = options;
        this.answer = answer;
        this.correctAttempts = correctAttempts;
        this.failedAttempts = failedAttempts;
        this.visited = visited;
        this.batch = batch;
    }

    public String getQuestion(){
        return question;
    }

    public List<String> getListOption(){
        if(listOptions == null || listOptions.isEmpty()){
            listOptions = getOptionAsList();
        }
        return listOptions;
    }

    public String getAnswer(){
        return answer;
    }

    private List<String> getOptionAsList(){
        List<String> options = new ArrayList<>();
        options.addAll(Arrays.asList(this.options.split("#")));
        return options;
    }

    public JsonQuestion getAsJsonQuestion(){
        if(listOptions == null || listOptions.isEmpty()){
            listOptions = getOptionAsList();
        }
        return new JsonQuestion(id,
                question,
                listOptions,
                answer,
                correctAttempts,
                failedAttempts,
                visited,
                batch);
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getCorrectAttempts() {
        return correctAttempts;
    }

    public void setCorrectAttempts(Integer correctAttempts) {
        this.correctAttempts = correctAttempts;
    }

    public Integer getFailedAttempts() {
        return failedAttempts;
    }

    public void setFailedAttempts(Integer failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }
}
