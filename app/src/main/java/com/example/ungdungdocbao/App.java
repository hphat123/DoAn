package com.example.ungdungdocbao;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    public static Context mContext;

    @Override
    public void onCreate() {
        this.mContext = this;
        super.onCreate();
    }
    public  static Context getContext() {return  mContext; }
}
