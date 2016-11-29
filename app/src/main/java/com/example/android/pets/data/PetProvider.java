package com.example.android.pets.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by dnj on 11/28/16.
 */

public class PetProvider extends ContentProvider {

    // db helper object
    private PetDbHelper mDBHELPER;

    /** Tag for the log messages */
    public static final String LOG_TAG = PetProvider.class.getSimpleName();

    @Override
    public boolean onCreate() {
        mDBHELPER = new PetDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
