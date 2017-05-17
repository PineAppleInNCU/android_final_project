package com.deitel.flagquiz.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tommy522588 on 2017/5/17.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Android_small_project";
    private static final int DB_VERSION = 2;
    private static final String TABLE_NAME = "Cats";
    private static final String COL_id = "id";
    private static final String COL_category = "cat_category";
    private static final String COL_introduction = "cat_introduction";
    private static final String COL_image_path = "image_path";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " ( " +
                    COL_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_category + " TEXT NOT NULL, " +
                    COL_introduction + " TEXT, " +
                    COL_image_path + " TEXT    ); ";

    public MySQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }



    /*
        把spot改掉，改成一個適合我們的物件
        把seed塞到onCreate(應該是可以)
     */



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public List<Cat> getAllCats() {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {
                COL_id, COL_category, COL_introduction, COL_image_path
        };
        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null,
                null);
        List<Cat> catList = new ArrayList<Cat>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String cat_category = cursor.getString(1);
            String cat_introduction = cursor.getString(2);
            String image_path = cursor.getString(3);

            Cat cat = new Cat(id, cat_category, cat_introduction, image_path);
            catList.add(cat);
        }
        cursor.close();
        return catList;
    }

    public Cat findById(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String[] columns = {
                COL_category, COL_introduction, COL_image_path
        };
        String selection = COL_id + " = ?;";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs,
                null, null, null);
        Cat cat = null;
        if (cursor.moveToNext()) {
            String cat_category = cursor.getString(0);
            String cat_introduction = cursor.getString(1);
            String image_path = cursor.getString(2);

            cat = new Cat(id,cat_category, cat_introduction, image_path);
        }
        cursor.close();
        return cat;
    }

    public long insert(Cat cat) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_category, cat.getCat_category());
        values.put(COL_introduction, cat.getCat_introduction());
        values.put(COL_image_path, cat.getImage_path());

        return db.insert(TABLE_NAME, null, values);
    }

    public int update(Cat cat) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_category, cat.getCat_category());
        values.put(COL_introduction, cat.getCat_introduction());
        values.put(COL_image_path, cat.getImage_path());

        String whereClause = COL_id + " = ?;";
        String[] whereArgs = {Integer.toString(cat.getId())};
        return db.update(TABLE_NAME, values, whereClause, whereArgs);
    }

    public int deleteById(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = COL_id + " = ?;";
        String[] whereArgs = {String.valueOf(id)};
        return db.delete(TABLE_NAME, whereClause, whereArgs);
    }

}
