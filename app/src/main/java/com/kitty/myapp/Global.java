package com.kitty.myapp;

import android.app.Application;

public class Global extends Application {

    private int user;
    public String str;
    public static String str1;

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public int getUser() {

        return user;
    }

    public void setUser(int user1) {

        user = user1;

    }
}