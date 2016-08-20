package com.bussssco.applications.buscoolversion1.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;

import com.bussssco.applications.buscoolversion1.model.databasesqlite.ClientCursorWrapper;
import com.bussssco.applications.buscoolversion1.model.databasesqlite.ClientDatabaseHelper;
import com.bussssco.applications.buscoolversion1.model.databasesqlite.DbSchema.MyTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by youssef on 8/20/2016.
 */
public class ClientLab {
    private static ClientLab sClientLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    private ClientLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new ClientDatabaseHelper(mContext).getWritableDatabase();
    }
    public static ClientLab get(Context context){
        if(sClientLab == null){
            sClientLab = new ClientLab(context);

        }
        return sClientLab;
    }
    public void addClient(Client client){
        ContentValues contentValues = getContentValues(client);
        mDatabase.insert(MyTable.NAME,null,contentValues);
    }
    public List<Client> getClientsInATrip(int tripId){
        List<Client> clients = new ArrayList<>();
        String[] args = {String.valueOf(tripId)};
        ClientCursorWrapper cursor = queryClients(MyTable.Cols.TRIP_ID +" = ?" ,args);
        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                Client curClient = cursor.getClient();
                clients.add(curClient);
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return clients;
    }
    public Client getClient(UUID id){
        ClientCursorWrapper cursorWrapper = queryClients(MyTable.Cols.UUID +" = ?",new String[]{id.toString()});
        try{
            if(cursorWrapper.getCount()==0){
                return null;
            }
            cursorWrapper.moveToFirst();
            return cursorWrapper.getClient();
        }finally {
            cursorWrapper.close();
        }
    }
    public void updateClient(Client client){
        String uuidString = client.getClientUUID().toString();
        ContentValues values = getContentValues(client);
        mDatabase.update(MyTable.NAME,values,
                MyTable.Cols.UUID + " = ? ",
                new String[]{uuidString.toString()});
    }
    public void removeClient(Client client){
        mDatabase.delete(MyTable.NAME, MyTable.Cols.UUID  + " = ?", new String[]{client.getClientUUID().toString()});
    }

    private static ContentValues getContentValues(Client client){
        ContentValues values = new ContentValues();
        values.put(MyTable.Cols.UUID,client.getClientUUID().toString());
        values.put(MyTable.Cols.PHONE_NUMBER,client.getPhoneNumber());
        values.put(MyTable.Cols.STUDENT_NAME,client.getStudentName());
        values.put(MyTable.Cols.TRIP_ID,client.getTripNumber());
        Location loc = client.getStudentLocation();
        String locationFormatted = loc.getLatitude() +";"+loc.getLongitude();
        values.put(MyTable.Cols.STUDENTLOCATION,locationFormatted);
        return values;
    }

    private ClientCursorWrapper queryClients(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                MyTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new ClientCursorWrapper(cursor);
    }
}
