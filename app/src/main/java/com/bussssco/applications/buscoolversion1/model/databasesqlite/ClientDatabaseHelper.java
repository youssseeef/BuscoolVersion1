package com.bussssco.applications.buscoolversion1.model.databasesqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bussssco.applications.buscoolversion1.model.databasesqlite.DbSchema.MyTable;

/**
 * Created by youss on 8/20/2016.
 */
public class ClientDatabaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME ="Clientdatabase.db";

    public ClientDatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + MyTable.NAME +"(" +
        "  _id integer primary key autoincrement, " +
                MyTable.Cols.UUID +", " +
                MyTable.Cols.TRIP_ID+ ", " +
                MyTable.Cols.PHONE_NUMBER+", " +
                MyTable.Cols.STUDENTLOCATION+ ", " +
                MyTable.Cols.STUDENT_NAME+
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
