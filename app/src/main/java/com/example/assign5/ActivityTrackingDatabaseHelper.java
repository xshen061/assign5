package com.example.assign5;
/**
 *  MIT License
 *
 *         Copyright (c) [year] [fullname]
 *
 *         Permission is hereby granted, free of charge, to any person obtaining a copy
 *         of this software and associated documentation files (the "Software"), to deal
 *         in the Software without restriction, including without limitation the rights
 *         to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *         copies of the Software, and to permit persons to whom the Software is
 *         furnished to do so, subject to the following conditions:
 *
 *         The above copyright notice and this permission notice shall be included in all
 *         copies or substantial portions of the Software.
 *
 *         THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *         IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *         FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *         AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *         LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *         OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *         SOFTWARE.
 */

/**
 * author: Cynthia Xia Sheng 2020
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * This class is the database information to save exercise
 */


public class ActivityTrackingDatabaseHelper extends SQLiteOpenHelper {
    public final static String LOGGER_NAME = "ChatDatabaseHelper";
    public final static String DATABASE_NAME = "T_Activity_DB";
    public final static String TABLE_NAME = "T_Activity_Table";
    public final static int VERSION_NUM = 3;
    public final static String ID = "_id";
    public final static String TYPE = "type";
    public final static String TIME = "time";
    public final static String DURATION = "duration";
    public final static String COMMENT = "comment";

    public ActivityTrackingDatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_MSG = "CREATE TABLE " + TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + TYPE + " TEXT, "
                + TIME + " TEXT, "
                + DURATION + " INTEGER, "
                + COMMENT + " TEXT " + ")";
        db.execSQL(CREATE_TABLE_MSG);
        Log.i(LOGGER_NAME, "Table " + TABLE_NAME + " is created.");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        Log.i(LOGGER_NAME, "Table " + TABLE_NAME + " is upgraded, oldVersion= " + oldVer
                + " newVersion=" + newVer);
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME); //delete what was there previously
        onCreate(db);
        //  Log.i("ChatDatabaseHelper", "Calling onCreate");
        Log.i(LOGGER_NAME, "Table " + TABLE_NAME + " is downgraded, newVersion=" + newVersion + "oldVersion=" + oldVersion);

    }
}