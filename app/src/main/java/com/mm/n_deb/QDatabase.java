package com.mm.n_deb;

/**
 * Created by arun on 26/11/17.
 */


import android.content.Context;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {DBQuestion.class}, version = 16, exportSchema = false)
public abstract class QDatabase extends RoomDatabase {

    private static QDatabase INSTANCE;

    public abstract DBQuestionDao dbQuestionDao();

    public static QDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context, QDatabase.class, "questionsdatabase")
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
