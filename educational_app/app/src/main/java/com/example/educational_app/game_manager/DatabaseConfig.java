package com.example.educational_app.game_manager;

import android.provider.BaseColumns;

public final class DatabaseConfig {

    private DatabaseConfig() {
    }

    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_question";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_IMAGE_NAME = "image_name";
        public static final String COLUMN_OPTION_1 = "option_1";
        public static final String COLUMN_OPTION_2 = "option_2";
        public static final String COLUMN_OPTION_3 = "option_3";
        public static final String COLUMN_OPTION_4 = "option_4";
        public static final String COLUMN_ANSWER = "answer";
    }

    public static class High_Score_Table implements BaseColumns {
        public static final String TABLE_NAME = "quiz_high_score";
        public static final String COLUMN_PLAYER_NAME = "player_name";
        public static final String COLUMN_SCORE = "score";
    }

}
