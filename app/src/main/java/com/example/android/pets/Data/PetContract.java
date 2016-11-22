package com.example.android.pets.data;

import android.provider.BaseColumns;

/**
 * Created by dnj on 11/22/16.
 */

public final class PetContract {

    private PetContract() {
    }

    public static abstract class PetEntry implements BaseColumns {

        public final static String TABLE_NAME = "Pets";

        // constants for table headers
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_PET_NAME = "name";
        public final static String COLUMN_PET_BREED = "breed";
        public final static String COLUMN_PET_GENDER = "gender";
        public final static String COLUMN_PET_WEIGHT = "weight";

        // constants for gender
        public final static int GENDER_UNKNOWN = 0;
        public final static int GENDER_MALE = 1;
        public final static int GENDER_FEMALE = 2;

        // constant to create DB entries
        private static final String TEXT_TYPE = " TEXT";
        private static final String INT_TYPE = " INTEGER";
        private static final String COMMA_SEP = ",";
        /*
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_PET_NAME + TEXT_TYPE + COMMA_SEP +
                        COLUMN_PET_BREED + TEXT_TYPE + COMMA_SEP +
                        COLUMN_PET_GENDER + INT_TYPE + COMMA_SEP +
                        COLUMN_PET_WEIGHT + INT_TYPE + " )";

        // constant to drop table
        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + PetEntry.TABLE_NAME;*/


    }
}
