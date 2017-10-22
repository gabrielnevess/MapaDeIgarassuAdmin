package br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.constants.Constants;
import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.model.LocationModel;

public class DataBaseUtil extends ConnectionDataBaseUtil {

    protected SQLiteDatabase database;
    protected SQLiteStatement stmt;
    protected Cursor cursor;

    public DataBaseUtil(Context context) {
        super(context);
        ConnectionDataBaseUtil db = new ConnectionDataBaseUtil(context);
        database = db.getWritableDatabase();
    }

    public void dropTable() {
        database.execSQL(Constants.DROP_TABLE);
        onCreate(database);
    }

    public void insertLocation(LocationModel locationModel) {

        stmt = database.compileStatement(Constants.INSERT_ALL);
        stmt.bindString(1, locationModel.getName());
        stmt.bindDouble(2, locationModel.getLongitude());
        stmt.bindDouble(3, locationModel.getLatitude());
        stmt.bindString(4, locationModel.getAddress());
        stmt.bindString(5, locationModel.getDescription());
        stmt.executeInsert();
    }

    public LocationModel searchLocation(String name) {

        cursor = database.query(Constants.TABLE, new String[]{Constants.ID, Constants.NAME, Constants.ADDRESS,
                Constants.DESCRIPTION, Constants.LATITUDE, Constants.LONGITUDE}, Constants.NAME + " = \'" + name + "\' ", null, null, null, null);
        cursor.moveToNext();

        LocationModel locationModel = new LocationModel();
        locationModel.setId(cursor.getInt(0));
        locationModel.setName(cursor.getString(1));
        locationModel.setAddress(cursor.getString(2));
        locationModel.setDescription(cursor.getString(3));
        locationModel.setLatitude(cursor.getDouble(4));
        locationModel.setLongitude(cursor.getDouble(5));

        return locationModel;
    }
}
