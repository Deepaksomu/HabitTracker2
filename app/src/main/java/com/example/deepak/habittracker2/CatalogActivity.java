package com.example.deepak.habittracker2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.example.deepak.habittracker2.data.HabbitContract;
import com.example.deepak.habittracker2.data.HabbitDbHelper;

/**
 * Displays list of habbit that were entered and stored in the app.
 */
public class CatalogActivity extends AppCompatActivity {
    /**
     * Database helper that will provide us access to the database
     */
    private HabbitDbHelper mDbHelper;

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
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new HabbitDbHelper(this);
        displayDatabaseInfo();
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the habbit database.
     */
    private void displayDatabaseInfo() {

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                HabbitContract.HabbitEntry._ID,
                HabbitContract.HabbitEntry.COLUMN_PERSON_NAME,
                HabbitContract.HabbitEntry.COLUMN_PERSON_HABBIT,
                HabbitContract.HabbitEntry.COLUMN_PERSON_GENDER,
                HabbitContract.HabbitEntry.COLUMN_PERSON_TIME
        };
        Cursor cursor = db.query(HabbitContract.HabbitEntry.TABLE_NAME, projection, null, null, null, null, null);
        TextView displayView = (TextView) findViewById(R.id.text_view_pet);


        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // habbit table in the database).
            // Create a header in the Text View that looks like this:
            //
            // The habbit table contains <number of rows in Cursor> .
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("The Habbit table contains " + cursor.getCount() + " habbits of people.\n\n");
            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(HabbitContract.HabbitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabbitContract.HabbitEntry.COLUMN_PERSON_NAME);
            int breedColumnIndex = cursor.getColumnIndex(HabbitContract.HabbitEntry.COLUMN_PERSON_HABBIT);
            int genderColumnIndex = cursor.getColumnIndex(HabbitContract.HabbitEntry.COLUMN_PERSON_GENDER);
            int weightColumnIndex = cursor.getColumnIndex(HabbitContract.HabbitEntry.COLUMN_PERSON_TIME);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentHabbit = cursor.getString(breedColumnIndex);
                int currentGender = cursor.getInt(genderColumnIndex);
                int currentTime = cursor.getInt(weightColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentHabbit + " - " +
                        currentGender + " - " +
                        currentTime));
            }

        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    private void insertHabbit() {

// Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and Toto's pet attributes are the values.
        ContentValues values = new ContentValues();
        values.put(HabbitContract.HabbitEntry.COLUMN_PERSON_NAME, "Toto");
        values.put(HabbitContract.HabbitEntry.COLUMN_PERSON_HABBIT, "Terrier");
        values.put(HabbitContract.HabbitEntry.COLUMN_PERSON_GENDER, HabbitContract.HabbitEntry.GENDER_MALE);
        values.put(HabbitContract.HabbitEntry.COLUMN_PERSON_TIME, 7);
        long newRowId = db.insert(HabbitContract.HabbitEntry.TABLE_NAME, null, values);
    }
    public void deletetable(){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.execSQL("delete from "+ "person" );
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                // Do nothing for now
                insertHabbit();
                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                deletetable();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
