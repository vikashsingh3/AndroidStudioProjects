package com.example.guesstheceleb;

import android.graphics.drawable.shapes.RoundRectShape;

import com.example.guesstheceleb.game.Game;
import com.example.guesstheceleb.game.Question;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class GameUnitTest {
//    @Test
//    public void addition_isCorrect() {
//        assertEquals(4, 2 + 2);
//    }
    @Test
    public void testGame(){
        Question[] questions = new Question[3];
        String[] answers = {"bob", "alice", "camry"};

        for (int i =0; i<3; i++){
           String  answer  = answers[i];
           questions[i] = new Question(answer, null, answers);
           System.out.println("answers = " + answer);
        }
        Game game = new Game(questions);

        while (!game.isGameOver()){
            Question question = game.next();
//            Question question1 = game.next();
            game.updateScore(question.check("bob"));
        }
        assertEquals("Score: 1/3", game.getScore());
    }
}

