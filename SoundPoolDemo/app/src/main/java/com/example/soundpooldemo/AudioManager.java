package com.example.soundpooldemo;

import android.content.Context;
import android.media.SoundPool;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

// This code demonstrates one way to load several sounds
// into a sound pool. Each sound has a unique sampleId.
// SampleId's are not the same as the raw resource ids

public class AudioManager implements SoundPool.OnLoadCompleteListener {
    private Map<Sound, Integer> soundIds;
    private SoundPool pool;
    private int loadId;
    private boolean ready;

    AudioManager(Context context) {
        soundIds = new HashMap<>();
        pool = new SoundPool(10,
                android.media.AudioManager.STREAM_MUSIC,
                0);
        pool.setOnLoadCompleteListener(this);

        // load order matches Sound's declaration order
        pool.load(context, R.raw.laughing, 0);
        pool.load(context, R.raw.walking, 0);
        pool.load(context, R.raw.explosion, 0);
    }

    @Override
    public void onLoadComplete(SoundPool soundPool,
                               int sampleId, int status) {
        this.ready = status == 0;

        // Each time a load finishes, the next loadId
        // is used to determine which enum value to use
        Sound sound = Sound.values()[loadId++];
        Log.i("AudioManager", "loaded sound: " + sound);
        soundIds.put(sound, sampleId);
    }

    boolean isReady() {
        return ready;
    }

    void play(Sound sound) {
        Integer id = soundIds.get(sound);
        assert id != null;
        pool.play(id, 1, 1,
                1, 0, 1);
    }
}
