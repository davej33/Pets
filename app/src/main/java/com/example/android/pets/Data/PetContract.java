package com.example.android.pets.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by dnj on 11/22/16.
 */

public final class PetContract {

    private PetContract() {
    }

    // URI constants
    public final static String CONTENT_AUTHORITY = "com.example.android.pets";
    public final static Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_PETS = "pets";

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

        // URI constant
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PETS);

        // MIME types
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + PATH_PETS;
        public static final String CONTENT_DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE +"/" + CONTENT_AUTHORITY + PATH_PETS;

    }
}
