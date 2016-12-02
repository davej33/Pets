package com.example.android.pets;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.pets.data.PetContract.PetEntry;

/**
 * Created by dnj on 12/1/16.
 */

public class PetCursorAdapter extends CursorAdapter {

    public PetCursorAdapter(Context context, Cursor cursor){
        super(context, cursor, 0);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // get views to modify
        TextView nameView = (TextView) view.findViewById(R.id.name);
        TextView breedView = (TextView) view.findViewById(R.id.summary);

        // get column IDs from cursor
        int nameColumn = cursor.getColumnIndex(PetEntry.COLUMN_PET_NAME);
        int breedColumn = cursor.getColumnIndex(PetEntry.COLUMN_PET_BREED);

        // store strings
        String petName = cursor.getString(nameColumn);
        String petBreed = cursor.getString(breedColumn);

        Log.e("adapter", "name and breed " + petName + petBreed);
        // set strings to views
        nameView.setText(petName);
        breedView.setText(petBreed);
     }
}
