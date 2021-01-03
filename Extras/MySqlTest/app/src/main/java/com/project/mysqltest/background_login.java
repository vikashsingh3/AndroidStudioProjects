package com.project.mysqltest;

import android.content.AsyncTaskLoader;
import android.content.Context;

class background_login  {
    public static void run(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
