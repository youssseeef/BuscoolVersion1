package com.bussssco.applications.buscoolversion1.model.databasesqlite;

/**
 * Created by youss on 8/20/2016.
 */
public class DbSchema {
    public static final class MyTable{
        public static final String NAME = "trips";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String TRIP_ID = "tripid";
            public static final String STUDENT_NAME = "studentname";
            public static final String STUDENTLOCATION = "location";
            public static final String PHONE_NUMBER = "phone";
        }


    }
}
