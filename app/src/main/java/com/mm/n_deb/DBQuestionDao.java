package com.mm.n_deb;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by arun on 26/11/17.
 */
@Dao
public interface DBQuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addQuestion(DBQuestion q);

    @Query("select * from dbquestion where batch = :batch")
    List<DBQuestion> getQuestions(String batch);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateQuestion(DBQuestion q);

    @Query("select count(*) from dbquestion")
    int getCount();
}
