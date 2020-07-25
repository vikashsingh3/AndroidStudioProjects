package com.example.educational_app.game_helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.educational_app.game_manager.DatabaseConfig.High_Score_Table;
import com.example.educational_app.game_manager.DatabaseConfig.QuestionsTable;
import com.example.educational_app.game_manager.HighScorePlayer;
import com.example.educational_app.game_manager.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SqliteHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "Quiz.db";
    private SQLiteDatabase sql_db;

    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sql_db) {
        this.sql_db = sql_db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + "( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_IMAGE_NAME + " TEXT, " +
                QuestionsTable.COLUMN_OPTION_1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION_2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION_3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION_4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER + " TEXT )";

        sql_db.execSQL(SQL_CREATE_QUESTIONS_TABLE);

        final String SQL_CREATE_HIGH_SCORE_TABLE = "CREATE TABLE " +
                High_Score_Table.TABLE_NAME + "( " +
                High_Score_Table._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                High_Score_Table.COLUMN_PLAYER_NAME + " TEXT, " +
                High_Score_Table.COLUMN_SCORE + " INTEGER )";

        sql_db.execSQL(SQL_CREATE_HIGH_SCORE_TABLE);
        insert_data_in_tables();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (VERSION == 1) {
            sql_db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
            sql_db.execSQL("DROP TABLE IF EXISTS " + High_Score_Table.TABLE_NAME);
            onCreate(sql_db);
        }
    }

    private void insert_data_in_tables() {
        Question q1 = new Question("The tallest structure and building in the world", "Bhurj_Khalifa_Dubai.png", "Burj Khalifa", "One World Trade Center", "Petronas Tower", "Shanghai Tower", "Burj Khalifa");
        insertQuestion(q1);
        Question q2 = new Question("Located in the City of Westminster, the palace is often at the centre of state occasions and royal hospitality.", "Buckingham_Palace_England.png", "Blenheim Palace", "Buckingham Palace", "Windsor Castle", "Woodstock Palace", "Buckingham Palace");
        insertQuestion(q2);
        Question q3 = new Question("It is the tallest brick building in the world with a steel framework.", "chrysler_building_New_York.png", "Philadelphia City Hall", "Sears Tower", "Entisar Tower", "Chrysler Building", "Chrysler Building");
        insertQuestion(q3);
        Question q4 = new Question("World's highest glass floor panelled elevator & World's highest bar", "CN_Tower_Canada.png", "Willis Tower", "CN Tower", "Inco Superstack", "Stantec Tower", "CN Tower");
        insertQuestion(q4);
        Question q5 = new Question("Also known as the Flavian Amphitheatre and held 50,000 to 80,000 spectators.", "Colosseum_Rome_Italy.png", "Pula Arena", "Allianz Arena", "Colosseum", "White City Stadium", "Colosseum");
        insertQuestion(q5);
        Question q6 = new Question("This statue weighs 635 metric tons and is located at the peak of the 700-metre Corcovado mountain.", "Cristo_Redentor_Statue_Brazil.png", "Statue of Unity", "Spring Temple Buddha", "Statue of Liberty", "Christ the Redeemer", "Christ the Redeemer");
        insertQuestion(q6);
        Question q7 = new Question("In 2015, the most-visited paid monument in the world and it is 324 metres (1,063 ft) tall.", "Eiffel_Tower_Paris.png", "Eiffel Tower", "Empire State", "Tokyo Skytree", "Blackpool Tower", "Eiffel Tower");
        insertQuestion(q7);
        Question q8 = new Question("It is the oldest of the Seven Wonders of the Ancient World", "Great_Pyramid_of_Giza.png", "Great Pyramid of Giza", "Tomb of Alexander the Great", "Tomb of Genghis Khan", "Pillar tomb", "Great Pyramid of Giza");
        insertQuestion(q8);
        Question q9 = new Question("This is made up of 6,259 km sections of actual wall.", "Great_Wall_Of_China.png", "Gateway of Gubeikou Fortress", "Fortress Sarzanello", "Great Wall of China", "City walls of Athens", "Great Wall of China");
        insertQuestion(q9);
        Question q10 = new Question("One of the Seven Wonders of the Ancient World as listed by Hellenic culture.", "Hanging_Gardens_of_Babylon.png", "Butchart Gardens", "Bodnant Garden", "BROOKLYN BOTANIC GARDEN", "Hanging Garden", "Hanging Garden");
        insertQuestion(q10);
        Question q11 = new Question("The height of the tower is 56.67 metres (185.93 feet) on the high side and leans 4-degree.", "Leaning_Tower_Italy.png", "Leaning Tower", "Galata Tower", "Eiffel Tower", "CN Tower", "Leaning Tower");
        insertQuestion(q11);
        Question q12 = new Question("Built on River Thames with a length of 269m and 32m in width", "London_Bridge_London.png", "London Bridge", "Golden Gate Bridge", "Sydney Harbour Bridge", "Brooklyn Bridge", "London Bridge");
        insertQuestion(q12);
        Question q13 = new Question("It is composed of 27 free-standing marble-clad 'petals' arranged in clusters of three to form nine sides. A 2001, most visited building in the world.", "Lotus_Temple_India.png", "Sydney Opera House", "Auditorio de Tenerife", "Palau de les Arts Reina Sofia", "Lotus Temple", "Lotus Temple");
        insertQuestion(q13);
        Question q14 = new Question("Inaugrated in 1973, it is a multi-venue performing arts centre. Hosts over 1,500 performances annually, became a UNESCO World Heritage Site in 2007.", "Opera_House_Australia.png", "Sydney Opera House", "Auditorio de Tenerife", "Palau de les Arts Reina Sofia", "Lotus Temple", "Sydney Opera House");
        insertQuestion(q14);
        Question q15 = new Question("They were the tallest buildings in the world from 1998 to 2004. This remain the tallest twin towers in the world.", "Petronas_Kaula_Lumpur.png", "Willis Tower", "Petronas Towers", "World Trade Center", "Qutub Minar", "Petronas Towers");
        insertQuestion(q15);
        Question q16 = new Question("A UNESCO World Heritage Site, started construction of in 1199.", "Qutab_Minar_India.png", "Willis Tower", "Petronas Towers", "World Trade Center", "Qutub Minar", "Qutub Minar");
        insertQuestion(q16);
        Question q17 = new Question("Completed in 1561, UNESCO World Heritage Site", "Saint_Basil_Cathedral_Moscow.png", "St. Peter’s Basilica", "Church of the Nativity", "Saint Basil's Cathedral", "St Paul’s Cathedral", "Saint Basil's Cathedral");
        insertQuestion(q17);
        Question q18 = new Question("It is a telecommunications and observation tower (328 m) tall, built in 1994–1997.", "Sky_Tower_New_Zealand.png", "Sydney Tower", "Sky Tower", "Macau Tower", "Eiffel Tower", "Sky Tower");
        insertQuestion(q18);
        Question q19 = new Question("The statue is a figure of Libertas, a robed Roman liberty goddess. A UNESCO World Heritage Site.", "Statue_of_Liberty_New_York.png", "Statue of Unity", "Spring Temple Buddha", "Statue of Liberty", "The Motherland Calls", "Statue of Liberty");
        insertQuestion(q19);
        Question q20 = new Question("An ivory-white marble mausoleum, built in 1632-53. A UNESCO World Heritage Site.", "Taj_Mahal_India.png", "Petra", "Taj Mahal", "Colosseum", "Chichen Itza", "Taj Mahal");
        insertQuestion(q20);
        Question q21 = new Question("It was completely rebuilt twice, once after a devastating flood an act of arson, now it is of the Seven Wonders of the Ancient World", "Temple_of_Artemis.png", "Ranakpur Temple", "Temple of Artemis", "Lotus Temple", "Temple of the Emerald Buddha", "Temple of Artemis");
        insertQuestion(q21);
    }

    private void insertQuestion(Question question_details) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(QuestionsTable.COLUMN_QUESTION, question_details.getQuestion());
        contentValues.put(QuestionsTable.COLUMN_IMAGE_NAME, question_details.getImage_name());
        contentValues.put(QuestionsTable.COLUMN_OPTION_1, question_details.getOption_1());
        contentValues.put(QuestionsTable.COLUMN_OPTION_2, question_details.getOption_2());
        contentValues.put(QuestionsTable.COLUMN_OPTION_3, question_details.getOption_3());
        contentValues.put(QuestionsTable.COLUMN_OPTION_4, question_details.getOption_4());
        contentValues.put(QuestionsTable.COLUMN_ANSWER, question_details.getAnswer());
        sql_db.insert(QuestionsTable.TABLE_NAME, null, contentValues);
    }

    public List<Question> getAllQuestions(int limit_questions) {
        List<Question> questionList = new ArrayList<>();
        sql_db = getReadableDatabase();
        ArrayList<Integer> id_values = generate_random_numbers(limit_questions);
        StringBuilder id_filter = new StringBuilder();
        for (int i = 0; i < id_values.size(); i++) {
            id_filter.append(id_values.get(i)).append(",");
        }

        String sql_query = "SELECT * FROM " + QuestionsTable.TABLE_NAME +
                " WHERE _id IN (" + id_filter.substring(0, id_filter.length() - 1) + ")";
        Cursor cursor = sql_db.rawQuery(sql_query, null);
        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setImage_name(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_IMAGE_NAME)));
                question.setOption_1(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION_1)));
                question.setOption_2(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION_2)));
                question.setOption_3(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION_3)));
                question.setOption_4(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION_4)));
                question.setAnswer(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_ANSWER)));
                questionList.add(question);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return questionList;
    }

    public List<HighScorePlayer> getTopPlayer() {
        List<HighScorePlayer> playerList = new ArrayList<>();
        int rank_number = 1;
        sql_db = getReadableDatabase();
        String sql_query = "SELECT * FROM " + High_Score_Table.TABLE_NAME +
                " WHERE " + High_Score_Table.COLUMN_SCORE + " >0" +
                " ORDER BY " + High_Score_Table.COLUMN_SCORE + " DESC LIMIT 10";
        Cursor cursor = sql_db.rawQuery(sql_query, null);
        if (cursor.moveToFirst()) {
            do {
                HighScorePlayer highScorePlayer = new HighScorePlayer();
                highScorePlayer.setRank(rank_number);
                highScorePlayer.setPlayerName(cursor.getString(cursor.getColumnIndex(High_Score_Table.COLUMN_PLAYER_NAME)));
                highScorePlayer.setScore(cursor.getInt(cursor.getColumnIndex(High_Score_Table.COLUMN_SCORE)));
                rank_number++;
                playerList.add(highScorePlayer);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return playerList;
    }

    public void insertHighScore(String playerName, int score) {
        sql_db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(High_Score_Table.COLUMN_PLAYER_NAME, playerName);
        values.put(High_Score_Table.COLUMN_SCORE, score);
        sql_db.insert(High_Score_Table.TABLE_NAME, null, values);
        sql_db.close();
    }

    public void resetScore() {
        sql_db = getWritableDatabase();
        sql_db.delete(High_Score_Table.TABLE_NAME, null, null);
        sql_db.close();
    }

    private ArrayList<Integer> generate_random_numbers(int number) {
        sql_db = getReadableDatabase();
        int record_count = 0;
        String sql_query = "SELECT count(*) ROWSCOUNT FROM " + QuestionsTable.TABLE_NAME;
        Cursor cursor = sql_db.rawQuery(sql_query, null);

        if (cursor.moveToFirst()) {
            record_count = cursor.getInt(cursor.getColumnIndex("ROWSCOUNT"));
        }
        cursor.close();

        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> randomList = new ArrayList<>();
        for (int i = 1; i <= record_count; i++) {
            list.add(i);
        }
        Collections.shuffle(list);

        for (int i = 0; i < number; i++) {
            randomList.add(list.get(i));
        }
        return randomList;
    }
}

