package com.example.educational_app.game_helper;

public class Background {
    public static void run(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
