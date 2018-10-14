package com.example.android.project;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Asad Ur Rehman on 10/14/2018.
 */
@Dao
public interface DataAccessObject {

    @Insert
    public void addUser(User u);

    @Query("select * from user")
    public List<User> getUsers();

    @Update
    public void updateUser(User user);
}
