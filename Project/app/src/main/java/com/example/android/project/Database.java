package com.example.android.project;

import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Asad Ur Rehman on 10/14/2018.
 */
@android.arch.persistence.room.Database(entities = {User.class},version = 1)
public abstract class Database extends RoomDatabase {
    public abstract DataAccessObject myDAO();
}
