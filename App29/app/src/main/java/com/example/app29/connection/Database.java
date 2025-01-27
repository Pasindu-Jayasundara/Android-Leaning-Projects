package com.example.app29.connection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.app29.dto.LocationDTO;
import java.util.ArrayList;

public class Database  extends SQLiteOpenHelper {

    private static final String COL_ID="", COL_NAME="", COL_LATITUDE="", COL_LONGITUDE="", TABLE_NAME="";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `"+Database.TABLE_NAME+ "`("+
                Database.COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Database.COL_NAME+" TEXT, " +
                Database.COL_LATITUDE+" TEXT, " +
                Database.COL_LONGITUDE+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS `"+Database.TABLE_NAME+"`");
        onCreate(db);
    }

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public Database(Context context){
        super(context, null, null, 0);
    }

    public boolean addLocation(LocationDTO locationDTO){
        SQLiteDatabase writableDB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Database.COL_NAME,locationDTO.getName());
        contentValues.put(Database.COL_LATITUDE,locationDTO.getLatitude());
        contentValues.put(Database.COL_LONGITUDE,locationDTO.getLongtitude());

        long inserted = writableDB.insert(Database.TABLE_NAME, null, contentValues);

        return inserted != -1;
    }

    public void addLocations(ArrayList<LocationDTO> dtoList){
        SQLiteDatabase writableDB = this.getWritableDatabase();

        for (LocationDTO locationDTO:dtoList){
            ContentValues contentValues = new ContentValues();
            contentValues.put(Database.COL_NAME,locationDTO.getName());
            contentValues.put(Database.COL_LATITUDE,locationDTO.getLatitude());
            contentValues.put(Database.COL_LONGITUDE,locationDTO.getLongtitude());

            writableDB.insert(Database.TABLE_NAME, null, contentValues);

        }
    }

    public ArrayList<LocationDTO> getLocations(){

        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        ArrayList<LocationDTO> locations = new ArrayList<>();

        Cursor cursor = readableDatabase.rawQuery("SELECT * FROM `"+Database.TABLE_NAME+"`",null);
        if(cursor.moveToFirst()){
            do{
                LocationDTO dto = new LocationDTO(
                        cursor.getString(1),
                        cursor.getDouble(2),
                        cursor.getDouble(3));

                locations.add(dto);

            }while (cursor.moveToNext());
        }
        return locations;
    }


    public LocationDTO getLocationsById(int id) {
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery("SELECT * FROM `"+Database.TABLE_NAME+"` WHERE `id`="+Database.COL_ID,null);
        ....................................
        return null;
    }
}
