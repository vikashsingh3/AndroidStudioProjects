package com.example.guesstheceleb;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.guesstheceleb.game.CelebrityManager;
import com.example.guesstheceleb.game.Difficulty;
import com.example.guesstheceleb.game.Game;
import com.example.guesstheceleb.game.GameBuilder;
import com.example.guesstheceleb.game.Question;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class GameInstrumentedTest {
//    @Test
//    public void useAppContext() {
//        // Context of the app under test.
//        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
//
//        assertEquals("com.example.guesstheceleb", appContext.getPackageName());
//    }
    @Test
    public void testGameBuilder() throws IOException {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        AssetManager assetManager = context.getAssets();
        CelebrityManager celebrityManager = new CelebrityManager(assetManager, "celebs");

        GameBuilder gameBuilder = new GameBuilder(celebrityManager);
        Game game = gameBuilder.create(Difficulty.EXPERT);

        int correctlyans = 0;
        loop: while(!game.isGameOver()){
            Question question = game.next();

            for (int i=0; i<celebrityManager.count(); i++){
                String name = celebrityManager.getName(i);
                Log.i("Game test", name);
                if (question.check(name)){
                    ++correctlyans;
                    continue loop;
                }
            }
            fail("didn't answer question correctly");
        }
        assertEquals(game.count(), correctlyans);
    }
}
