package com.example.educational_app;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.educational_app.game_helper.SqliteHelper;
import com.example.educational_app.game_manager.HighScorePlayer;
import com.example.educational_app.game_manager.Question;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class QuizInstrumentedTest {
    private Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    private AssetManager assetManager = context.getAssets();

    @Test
    public void testDatabase_getQuestions() {
        // test to retrieve number of questions
        SqliteHelper sqliteHelper = new SqliteHelper(context);
        int number_of_questions;
        int question_returned_count;
        int total_question_count = 500;
        List<Question> questionList;

        questionList = sqliteHelper.getAllQuestions(total_question_count);
        total_question_count = questionList.size();

        for (number_of_questions = 1; number_of_questions <= total_question_count; number_of_questions++) {
            questionList = sqliteHelper.getAllQuestions(number_of_questions);
            question_returned_count = questionList.size();

            Log.i("InstrumentedTest",
                    "number_of_questions " + number_of_questions +
                            ", question_returned_count " + question_returned_count);
            assertEquals(number_of_questions, question_returned_count);
        }
    }

    @Test
    public void testDatabase_insertPlayersScores() {
        // Insert some dummy data for players with scores
        SqliteHelper sqliteHelper = new SqliteHelper(context);
        int counter;
        List<HighScorePlayer> playerList;
        String[] player_names = {"Player 1", "Player 2", "Player 3", "Player 4", "Player 5", "Player 5"};
        int[] int_scores = new int[]{10, 20, 30, 40, 30, 20};
        int new_record_count, record_count;

        sqliteHelper.resetScore();
        playerList = sqliteHelper.getTopPlayer();
        record_count = playerList.size();

        for (counter = 0; counter < player_names.length; counter++) {
            sqliteHelper.insertHighScore(player_names[counter], int_scores[counter]);
            playerList = sqliteHelper.getTopPlayer();
            new_record_count = playerList.size();

            Log.i("InstrumentedTest", "Total records " + record_count + " " + new_record_count);
            assertEquals(record_count + 1, new_record_count);
            record_count = new_record_count;
        }
    }

    @Test
    public void testDatabase_getTopPlayers() {
        // Insert some dummy data for players with scores
        SqliteHelper sqliteHelper = new SqliteHelper(context);
        int counter;
        int new_record_count;
        List<HighScorePlayer> playerList;

        String[] player_names = {
                "Player 1", "Player 2", "Player 3", "Player 4", "Player 5",
                "Player 5", "Player 5", "Player 5", "Player 5", "Player 5",
                "Player 6", "Player 7", "Player 8", "Player 9", "Player 10",
                "Player 11", "Player 12", "Player 13", "Player 14", "Player 15",
                "Player 16", "Player 17", "Player 18", "Player 19", "Player 20",
                "Player 21", "Player 22", "Player 23", "Player 24", "Player 25"};

        int[] int_scores = new int[]{
                10, 20, 30, 40, 30, 20, 50, 60, 50, 10, 20, 10, 20, 30, 40,
                10, 20, 30, 40, 80, 20, 50, 60, 20, 10, 20, 10, 20, 30, 40};

        sqliteHelper.resetScore();
        for (counter = 0; counter < player_names.length; counter++) {
            sqliteHelper.insertHighScore(player_names[counter], int_scores[counter]);
        }

        playerList = sqliteHelper.getTopPlayer();
        new_record_count = playerList.size();

        String playerName;
        int rank, score;

        //assert the top 10 player name, rank and scores
        for (counter = 0; counter < new_record_count; counter++) {
            HighScorePlayer currentPlayer = playerList.get(counter);

            rank = currentPlayer.getRank();
            playerName = currentPlayer.getPlayerName();
            score = currentPlayer.getScore();

            assertEquals(counter + 1, rank);
            switch (counter) {
                case 0:
                    assertEquals("Player 15", playerName);
                    assertEquals(80, score);
                    break;
                case 1:
                    assertEquals("Player 5", playerName);
                    assertEquals(60, score);
                    break;
                case 2:
                    assertEquals("Player 18", playerName);
                    assertEquals(60, score);
                    break;
                case 3:
                case 4:
                    assertEquals("Player 5", playerName);
                    assertEquals(50, score);
                    break;
                case 5:
                    assertEquals("Player 17", playerName);
                    assertEquals(50, score);
                    break;
                case 6:
                    assertEquals("Player 4", playerName);
                    assertEquals(40, score);
                    break;
                case 7:
                    assertEquals("Player 10", playerName);
                    assertEquals(40, score);
                    break;
                case 8:
                    assertEquals("Player 14", playerName);
                    assertEquals(40, score);
                    break;
                case 9:
                    assertEquals("Player 25", playerName);
                    assertEquals(40, score);
                    break;
                default:
                    assertNotEquals("Player 99", playerName);
            }
        }
    }

    @Test
    public void testImage() {
        // For Positive test cases
        String[] positive_image_name = {"Bhurj_Khalifa_Dubai.png", "Buckingham_Palace_England.png",
                "chrysler_building_New_York.png", "CN_Tower_Canada.png",
                "Colosseum_Rome_Italy.png", "Cristo_Redentor_Statue_Brazil.png",
                "Eiffel_Tower_Paris.png", "Great_Pyramid_of_Giza.png",
                "Great_Wall_Of_China.png", "Hanging_Gardens_of_Babylon.png",
                "Leaning_Tower_Italy.png", "London_Bridge_London.png",
                "Lotus_Temple_India.png", "Opera_House_Australia.png",
                "Petronas_Kaula_Lumpur.png", "Qutab_Minar_India.png",
                "Saint_Basil_Cathedral_Moscow.png", "Sky_Tower_New_Zealand.png",
                "Statue_of_Liberty_New_York.png", "Taj_Mahal_India.png",
                "Temple_of_Artemis.png"};

        // For Negative test cases
        String[] negative_image_name = {"Bhurj_Khalifa_Dubai.pn", "buckingham_Palace_England.png",
                "chrysler_building New_York.png", "CN-Tower_Canada.png",
                "Colosseum_RomeItaly.png", "Cristo Redentor_Statue_Brazil.png",
                "Eiffel_Tower__Paris.png", "GreatPyramid_of_Giza.png",
                "Great_Wall_of_China.png", "Hanging_Garden_of_Babylon.png",
                "Leaning_tower_Italy.png", "London_Bridge_old.png",
                "Lotus_TempLe_India.png", "Opera_Houses_Australia.png",
                "PetronasKaula_Lumpur.png", "Qutab__Minar_India.png",
                "Saint_Basil_cathedral_Moscow.png", "Sky_Towers_New_Zealand.png",
                "Statue_of_Lberty_New_York.png", "Taj_Mahal-India.png",
                "Temple_of_Artems.png"};

        test_images(positive_image_name, "positive", assetManager);
        test_images(negative_image_name, "negative", assetManager);
    }

    public void test_images(String[] image_name, String case_type, AssetManager assetManager) {

        System.out.println("Testing " + case_type + " test cases *************");
        for (String s : image_name) {
            Bitmap image_bitmap = getImageBitmap(assetManager, s);
            if (case_type.equals("positive")) {
                assertNotNull(image_bitmap);
            } else {
                assertNull(image_bitmap);
            }
        }
    }

    private Bitmap getImageBitmap(AssetManager assetManager, String imageName) {
        try {
            String path = "places/" + imageName;
            InputStream stream = assetManager.open(path);
            Bitmap bitmap = BitmapFactory.decodeStream(stream);
            stream.close();
            return bitmap;
        } catch (IOException e) {
            Log.i("GameActivity", "Unable to open image");
            return null;
        }
    }
}
