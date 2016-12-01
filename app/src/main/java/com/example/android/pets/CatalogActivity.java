/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.pets;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.android.pets.data.PetContract.PetEntry;
import com.example.android.pets.data.PetDbHelper;

/**
 * Displays list of pets that were entered and stored in the app.
 */
public class CatalogActivity extends AppCompatActivity {

    private PetDbHelper mDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        //displayDatabaseInfo();

        mDbHelper = new PetDbHelper(this);
        displayDatabaseInfo();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the pets database.
     */
    private void displayDatabaseInfo() {

        String[] projection = {
                PetEntry._ID,
                PetEntry.COLUMN_PET_NAME,
                PetEntry.COLUMN_PET_BREED,
                PetEntry.COLUMN_PET_GENDER,
                PetEntry.COLUMN_PET_WEIGHT};
        // String selection = PetEntry.COLUMN_PET_GENDER + "=?";
        // String[] selectionArgs = {String.valueOf(PetEntry.GENDER_MALE)};

        // Cursor cursor = db.query(PetEntry.TABLE_NAME, null, null, null, null, null, null);

        Cursor cursor = getContentResolver().query(
                PetEntry.CONTENT_URI,   // content uri for pets table
                projection,             // columns to return
                null,                   // selection criteria
                null,                   // selection criteria
                null);                  // sort criteria

        TextView displayView = (TextView) findViewById(R.id.text_view_pet);
        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // pets table in the database).

            displayView.setText("The table has " + cursor.getCount() + " pets.\n\n");

            int idColumnIndex = cursor.getColumnIndex(PetEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(PetEntry.COLUMN_PET_NAME);
            int breedColumn = cursor.getColumnIndex(PetEntry.COLUMN_PET_BREED);
            int genderColumn = cursor.getColumnIndex(PetEntry.COLUMN_PET_GENDER);
            int weightColumn = cursor.getColumnIndex(PetEntry.COLUMN_PET_WEIGHT);

            displayView.append("ID" + " - " + "Name" + " - " + "Breed" + " - " + "Gender" + " - " + "Weight\n");

            while(cursor.moveToNext()){

                int currentID = cursor.getInt(idColumnIndex);
                String name = cursor.getString(nameColumnIndex);
                String breed = cursor.getString(breedColumn);
                int gender = cursor.getInt(genderColumn);
                String genderString = "";
                if (gender == 0){
                    genderString = "Unknown";
                } else if (gender == 1){
                    genderString = "Male";
                } else {
                    genderString = "Female";
                }
                int weight = cursor.getInt(weightColumn);

                displayView.append("\n" + currentID + " - " + name + " - " + breed + " - "
                                    + genderString + " - " + weight + " kg");
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    private void insertPet(){

        ContentValues dummyPet = new ContentValues();
        dummyPet.put(PetEntry.COLUMN_PET_NAME, "Toto");
        dummyPet.put(PetEntry.COLUMN_PET_BREED, "Terrier");
        dummyPet.put(PetEntry.COLUMN_PET_GENDER, PetEntry.GENDER_MALE);
        dummyPet.put(PetEntry.COLUMN_PET_WEIGHT, 7);

        Uri newRowID = getContentResolver().insert(PetEntry.CONTENT_URI, dummyPet);

       /* if(newRowID == null){
            Toast.makeText(this, "Error adding pet to DB", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Pet added", Toast.LENGTH_SHORT).show();
        } */


    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertPet();
                displayDatabaseInfo();

                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}