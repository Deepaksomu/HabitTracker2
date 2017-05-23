package com.example.deepak.habittracker2.data;

/**
 * Created by Deepak on 23-05-2017.
 */

import android.provider.BaseColumns;

/**
 * API Contract for the  app.
 */
public final class HabbitContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private HabbitContract() {
    }

    /**
     * Inner class that defines constant values for the pets database table.
     * Each entry in the table represents a single person.
     */
    public static final class HabbitEntry implements BaseColumns {

        /**
         * Name of database table for person
         */
        public final static String TABLE_NAME = "person";

        /**
         * Unique ID number for the pet (only for use in the database table).
         * <p/>
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the person.
         * <p/>
         * Type: TEXT
         */
        public final static String COLUMN_PERSON_NAME = "name";

        /**
         * Habbit of the person.
         * <p/>
         * Type: TEXT
         */
        public final static String COLUMN_PERSON_HABBIT = "habit";

        /**
         * Gender of the person.
         * <p/>
         * The only possible values are {@link #GENDER_UNKNOWN}, {@link #GENDER_MALE},
         * or {@link #GENDER_FEMALE}.
         * <p/>
         * Type: INTEGER
         */
        public final static String COLUMN_PERSON_GENDER = "gender";

        /**
         * time of person.
         * <p/>
         * Type: INTEGER
         */
        public final static String COLUMN_PERSON_TIME = "time";

        /**
         * Possible values for the gender
         */
        public static final int GENDER_UNKNOWN = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;
    }

}

