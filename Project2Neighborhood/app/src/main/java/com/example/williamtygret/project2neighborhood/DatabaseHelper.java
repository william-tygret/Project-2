package com.example.williamtygret.project2neighborhood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by williamtygret on 2/2/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String SEARCH_DB = "SEARCH_DB";
    public static final String PLACES_TABLE_NAME = "PLACES_LIST";

    public static final String COL_ID = "_id";
    public static final String COL_PLACE_NAME = "PLACE_NAME";
    public static final String COL_PLACE_ADDRESS = "ADDRESS";
    public static final String COL_PLACE_BOROUGH = "BOROUGH";
    public static final String COL_PLACE_NEIGHBORHOOD = "NEIGHBORHOOD";
    public static final String COL_PLACE_TYPE = "TYPE";
    public static final String COL_PLACE_DESCRIPTION = "DESCRIPTION";

    private Context mHelperContext;

    public static final String[] PLACES_COLUMNS = {COL_ID,COL_PLACE_NAME,COL_PLACE_BOROUGH,COL_PLACE_ADDRESS,COL_PLACE_NEIGHBORHOOD,COL_PLACE_TYPE,COL_PLACE_DESCRIPTION};

    private static final String CREATE_PLACES_TABLE =
            "CREATE TABLE " + PLACES_TABLE_NAME +
                    "(" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_PLACE_NAME + " TEXT, " +
                    COL_PLACE_ADDRESS + " TEXT, " +
                    COL_PLACE_BOROUGH + " TEXT, " +
                    COL_PLACE_NEIGHBORHOOD + " TEXT, "+
                    COL_PLACE_TYPE + " TEXT, "+
                    COL_PLACE_DESCRIPTION + " TEXT )";

    private static DatabaseHelper instance;

    public static DatabaseHelper getInstance(Context context){
        if(instance == null){
            instance = new DatabaseHelper(context);
        }
        return instance;
    }

    private DatabaseHelper(Context context) {
        super(context, SEARCH_DB, null, DATABASE_VERSION);
        mHelperContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PLACES_TABLE);//"CREATE TABLE place (id INTEGER PRIMARY KEY, name TEXT, address TEXT, borough TEXT, neighborhood TEXT, type TEXT, description TEXT)");

       insertPlace(1, "Super Kyle's Pizza","471 Frederick Douglas Blvd.", "Manhattan", "Harlem", "Pizza", "An awesome little pizza place located in beautiful Harlem");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//upgrades the current database
        db.execSQL("DROP TABLE IF EXISTS place");
        onCreate(db);
    }

    public Cursor getPlacesList(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(PLACES_TABLE_NAME, // a. table
                PLACES_COLUMNS, // b. column names
                null, // c. selections
                null, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        Log.d("Inside DatabaseHelper ","Found "+cursor.getCount()+" items");
        return cursor;
    }



    public void insertPlace(int id, String name, String address, String borough, String neighborhood, String type, String description){
        SQLiteDatabase db = getWritableDatabase();

        //INSERT INTO games VALUES (1, "Super Smash Bros Melee", 2001)
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("name", name);
        values.put("address", address);
        values.put("borough", borough);
        values.put("neighborhood", neighborhood);
        values.put("type", type);
        values.put("description", description);
        Log.d("DatabaseHelper insert",values.toString());
        db.insert("PLACES_LIST", null, values);
    }


    public Cursor searchPlaces(String query){

        Log.d("searchplaces","did we get here?");
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("searchplaces","did we get here also?");

        Cursor cursor = db.query(PLACES_TABLE_NAME, // a. table
                PLACES_COLUMNS, // b. column names
                COL_PLACE_NAME+" LIKE ?", // c. selections
                new String[]{"%"+query+"%"}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        return cursor;
    }

    public String getPlaceName(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(PLACES_TABLE_NAME, // a. table
                new String[] {COL_PLACE_NAME}, // b. column names
                COL_ID + "= ?", // c. selections
                new String[]{String.valueOf(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        if(cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(COL_PLACE_NAME));
        }else{
            return "Description Not Found";
        }
    }
//
//    public Place getPlace(int id){
//        SQLiteDatabase db = getReadableDatabase();
//
//        //SELECT name, year FROM games
//        String [] columns = new String[]{"id", "name", "address", "borough", "neighborhood", "type"};
//
//        //WHERE id = 1
//        String selection = "id = ?";
//
//        String[] selectionArgs = new String[]{String.valueOf(id)};
//        Cursor cursor = db.query("place", columns, selection, selectionArgs, null, null, null, null);
//
//        cursor.moveToFirst();
//
//        String name = cursor.getString(cursor.getColumnIndex("name"));
//        String address = cursor.getString(cursor.getColumnIndex("address"));
//        String borough = cursor.getString(cursor.getColumnIndex("borough"));
//        String neighborhood = cursor.getString(cursor.getColumnIndex("neighborhood"));
//        String type = cursor.getString(cursor.getColumnIndex("type"));
//
//        return new Place(id, name, address, borough, neighborhood, type);
//    }
//
//    public ArrayList<Place> getAllPlaces(){
//        SQLiteDatabase db = getReadableDatabase();
//
//        //SELECT name, year FROM games
//        String [] columns = new String[]{"id", "name", "address", "borough", "neighborhood", "type"};
//
//        Cursor cursor = db.query("place", columns, null, null, null, null, null, null);
//
//        cursor.moveToFirst();
//
//        ArrayList<Place> place = new ArrayList<Place>();
//
//        while (!cursor.isAfterLast()){
//            int id = cursor.getInt(cursor.getColumnIndex("id"));
//            String name = cursor.getString(cursor.getColumnIndex("name"));
//            String address = cursor.getString(cursor.getColumnIndex("address"));
//            String borough = cursor.getString(cursor.getColumnIndex("borough"));
//            String neighborhood = cursor.getString(cursor.getColumnIndex("neighborhood"));
//            String type = cursor.getString(cursor.getColumnIndex("type"));
//
//            place.add(new Place(id, name, address, borough, neighborhood, type));
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return place;
//    }
}
