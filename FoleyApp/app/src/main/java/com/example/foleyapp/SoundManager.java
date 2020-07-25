package com.example.foleyapp;

import android.content.Context;
import android.media.SoundPool;
import android.util.Log;

import com.example.foleyapp.Enums.EXTRAS;
import com.example.foleyapp.Enums.HUMAN;
import com.example.foleyapp.Enums.NATURE;
import com.example.foleyapp.Enums.VEHICLE;

import java.util.HashMap;
import java.util.Map;

public class SoundManager implements SoundPool.OnLoadCompleteListener {
    private Map<Sound, Integer> soundIds;
    private Map<HUMAN, Integer> human_soundIds;
    private Map<VEHICLE, Integer> vehicle_soundIds;
    private Map<NATURE, Integer> nature_soundIds;
    private Map<EXTRAS, Integer> extra_soundIds;
    private SoundPool pool, human_pool, nature_pool, vehicle_pool, extra_pool;
    private int loadId, natureId, humanId, vehicleId, extraId;
    private boolean ready;
    private String soundType;

    SoundManager(Context context) {
        // load order matches Sound's declaration order
        soundType = "Human";
        load_soundPool(context, "Human");
        soundType = "Nature";
        load_soundPool(context, "Nature");
        soundType = "Vehicle";
        load_soundPool(context, "Vehicle");
        soundType = "Extra";
        load_soundPool(context, "Extra");
    }

    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
        // Each time a load finishes, the next loadId
        // is used to determine which enum value to use
        if (loadId >= 0 && loadId < 4) {
            HUMAN human = HUMAN.values()[humanId++];
            human_soundIds.put(human, sampleId);
            this.ready = status == 0;
        } else if (loadId >= 4 && loadId < 12) {
            NATURE nature = NATURE.values()[natureId++];
            nature_soundIds.put(nature, sampleId);
            this.ready = status == 0;
        } else if (loadId >= 12 && loadId < 20) {
            VEHICLE vehicle = VEHICLE.values()[vehicleId++];
            vehicle_soundIds.put(vehicle, sampleId);
            this.ready = status == 0;
        } else if (loadId >= 20 && loadId < 27) {
            EXTRAS extras = EXTRAS.values()[extraId++];
            extra_soundIds.put(extras, sampleId);
            this.ready = status == 0;
        }
        loadId++;
    }

    boolean isReady() {
        return ready;
    }

    private void load_soundPool(Context context, String SoundName) {
        switch (SoundName) {
            case "Nature":
                soundType = "Nature";
                nature_soundIds = new HashMap<>();
                nature_pool = new SoundPool(10,
                        android.media.AudioManager.STREAM_MUSIC,
                        0);
                nature_pool.setOnLoadCompleteListener(this);

                nature_pool.load(context, R.raw.cartoon_birds, 0);
                nature_pool.load(context, R.raw.flock_of_seagulls, 0);
                nature_pool.load(context, R.raw.gibbon_monkey, 0);
                nature_pool.load(context, R.raw.water_drops, 0);
                nature_pool.load(context, R.raw.rain, 0);
                nature_pool.load(context, R.raw.waterphone, 0);
                nature_pool.load(context, R.raw.wind, 0);
                nature_pool.load(context, R.raw.night_sounds_crickets, 0);
                break;
            case "Vehicle":
                soundType = "Vehicle";
                vehicle_soundIds = new HashMap<>();
                vehicle_pool = new SoundPool(10,
                        android.media.AudioManager.STREAM_MUSIC,
                        0);
                vehicle_pool.setOnLoadCompleteListener(this);

                vehicle_pool.load(context, R.raw.alien_spaceship, 0);
                vehicle_pool.load(context, R.raw.fire_truck_air_horn, 0);
                vehicle_pool.load(context, R.raw.formula, 0);
                vehicle_pool.load(context, R.raw.muscle_car, 0);
                vehicle_pool.load(context, R.raw.steam_train, 0);
                vehicle_pool.load(context, R.raw.steam_train_whistle, 0);
                vehicle_pool.load(context, R.raw.steam_engine, 0);
                vehicle_pool.load(context, R.raw.healicopter_approach, 0);
                vehicle_pool.load(context, R.raw.railroad_crossing_bell, 0);
                break;
            case "Human":
                soundType = "Human";
                human_soundIds = new HashMap<>();
                human_pool = new SoundPool(10,
                        android.media.AudioManager.STREAM_MUSIC,
                        0);
                human_pool.setOnLoadCompleteListener(this);

                human_pool.load(context, R.raw.audience_applause, 0);
                human_pool.load(context, R.raw.evil_laugh, 0);
                human_pool.load(context, R.raw.evil_laugh_male, 0);
                human_pool.load(context, R.raw.i_love_you, 0);
                break;
            case "Extra":
                soundType = "Extra";
                extra_soundIds = new HashMap<>();
                extra_pool = new SoundPool(10,
                        android.media.AudioManager.STREAM_MUSIC,
                        0);
                extra_pool.setOnLoadCompleteListener(this);

                extra_pool.load(context, R.raw.european_dragon, 0);
                extra_pool.load(context, R.raw.grenade_launcher, 0);
                extra_pool.load(context, R.raw.incoming_suspense, 0);
                extra_pool.load(context, R.raw.service_bell, 0);
                extra_pool.load(context, R.raw.clock_chimes, 0);
                break;
            default:
                Log.i("Sound Load", "Unable to get the group name" + SoundName);
                break;
        }
    }

    void play_nature(NATURE sound) {
        Integer id = nature_soundIds.get(sound);
        assert id != null;
        nature_pool.play(id, 1, 1, 1, 0, 1);
    }

    void play_human(HUMAN sound) {
        Integer id = human_soundIds.get(sound);
        assert id != null;
        human_pool.play(id, 1, 1, 1, 0, 1);
    }

    void play_vehicle(VEHICLE sound) {
        Integer id = vehicle_soundIds.get(sound);
        assert id != null;
        vehicle_pool.play(id, 1, 1, 1, 0, 1);
    }

    void play_extra(EXTRAS sound) {
        Integer id = extra_soundIds.get(sound);
        assert id != null;
        extra_pool.play(id, 1, 1, 1, 0, 1);
    }
}
