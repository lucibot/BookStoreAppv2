package com.example.android.bookstoreapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BookStoreHelper extends SQLiteOpenHelper {
    /**
     * Database helper for BookStore app. Manages database creation and version management.
     */
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "bookstore.db";

    public BookStoreHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * onCreate method is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES = "CREATE TABLE " + BookContract.BookEntry.TABLE_NAME + "(" +
                BookContract.BookEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BookContract.BookEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + BookContract.BookEntry.COLUMN_PRICE + " INTEGER NOT NULL DEFAULT 0, "
                + BookContract.BookEntry.COLUMN_QUANTITY + " INTEGER NOT NULL DEFAULT 0, "
                + BookContract.BookEntry.COLUMN_SUPPLIER + " TEXT NOT NULL, "
                + BookContract.BookEntry.COLUMN_SUPPLIER_PHONE + " TEXT NOT NULL )";
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    /**
     * Upgrades database
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDownGrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);


    }

    /**
     * Deletes table and all elements.
     */
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
                    + BookContract.BookEntry.TABLE_NAME;
}