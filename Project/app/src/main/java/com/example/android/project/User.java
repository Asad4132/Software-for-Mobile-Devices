package com.example.android.project;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.view.autofill.AutofillId;

/**
 * Created by Asad Ur Rehman on 10/14/2018.
 */
@Entity(tableName = "user")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo (name = "userName")
    private String Name;

    @ColumnInfo (name = "userEmail")
    private String Email;

    @ColumnInfo (name = "userPassword")
    private String Password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
