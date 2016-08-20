package com.bussssco.applications.buscoolversion1.model.databasesqlite;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.bussssco.applications.buscoolversion1.model.Client;
import com.bussssco.applications.buscoolversion1.model.databasesqlite.DbSchema.MyTable;

/**
 * Created by youss on 8/20/2016.
 */
public class ClientCursorWrapper extends CursorWrapper {

    public ClientCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public Client getClient(){
        String uuid = getString(getColumnIndex(MyTable.Cols.UUID));
        String studentName = getString(getColumnIndex(MyTable.Cols.STUDENT_NAME));
        Integer tripId = Integer.parseInt(getString(getColumnIndex(MyTable.Cols.TRIP_ID)));
        String phone = getString(getColumnIndex(MyTable.Cols.PHONE_NUMBER));
        String location = getString(getColumnIndex(MyTable.Cols.STUDENTLOCATION));
        String[] parsedLocation = location.split(";");
        double latitude = Double.parseDouble(parsedLocation[0]);
        double longitude = Double.parseDouble(parsedLocation[1]);
        Client client = Client.newClient(tripId,studentName,phone,latitude,longitude );
        return client;


    }
}
