package com.example.guesstheceleb.game;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class CelebrityManager {
    private String assetPath;
    private String[] imageNames;
    private AssetManager assetManager;

    public CelebrityManager(AssetManager assetManager, String assetPath) {
        this.assetManager = assetManager;
        this.assetPath = assetPath;
        try {

            imageNames = this.assetManager.list(assetPath);
        } catch (IOException e) {
            Log.i("CelebrityManager Error:", "Unable to determine path");
        }
    }

    public String getName(int i) {
        return imageNames[i];
    }

    public int count() {
        return imageNames.length;
    }

    Bitmap getBitmap(int i) {
        try {
            String path = assetPath + "/" + imageNames[i];
            InputStream stream = assetManager.open(path);
            Bitmap bitmap = BitmapFactory.decodeStream(stream);
            stream.close();
            return bitmap;
        } catch (IOException e) {
            Log.i("CelebrityManager Error:", "Unable to get images");
            return null;
        }
    }
}
