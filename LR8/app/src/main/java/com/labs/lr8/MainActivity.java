/**
 * This is free and unencumbered software released into the public domain.
 *
 * Anyone is free to copy, modify, publish, use, compile, sell, or
 * distribute this software, either in source code form or as a compiled
 * binary, for any purpose, commercial or non-commercial, and by any
 * means.
 *
 * In jurisdictions that recognize copyright laws, the author or authors
 * of this software dedicate any and all copyright interest in the
 * software to the public domain. We make this dedication for the benefit
 * of the public at large and to the detriment of our heirs and
 * successors. We intend this dedication to be an overt act of
 * relinquishment in perpetuity of all present and future rights to this
 * software under copyright law.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 * For more information, please refer to <http://unlicense.org/>
 */

package com.labs.lr8;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Entry fields
    private EditText editTextName, editTextPhone, editTextNotes;

    // "List" of entries
    private TextView textViewEntries;

    // Database helper
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect buttons
        findViewById(R.id.buttonShow).setOnClickListener(v -> databaseShow());
        findViewById(R.id.buttonAdd).setOnClickListener(v -> databaseAddEntry());
        findViewById(R.id.buttonDel).setOnClickListener(v -> databaseDeleteEntry());
        findViewById(R.id.buttonClear).setOnClickListener(v -> databaseClear());

        // Initialize EditTexts and TextViews
        editTextName = findViewById(R.id.entryName);
        editTextPhone = findViewById(R.id.entryPhone);
        editTextNotes = findViewById(R.id.entryNotes);
        textViewEntries = findViewById(R.id.entriesList);

        // Initialize DBHandler class
        dbHandler = new DBHandler(this);
    }

    /**
     * Shows all table entries
     */
    private void databaseShow() {
        // Clear list of entries
        textViewEntries.setText("");

        String[] projection = {
                DBHandler.DB_COLUMN_NAME,
                DBHandler.DB_COLUMN_PHONE,
                DBHandler.DB_COLUMN_NOTES,
        };
        Cursor cursor = dbHandler.getWritableDatabase().query(
                DBHandler.DB_TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        // Add all entries
        int nameIndex = cursor.getColumnIndex(DBHandler.DB_COLUMN_NAME);
        int phoneIndex = cursor.getColumnIndex(DBHandler.DB_COLUMN_PHONE);
        int notesIndex = cursor.getColumnIndex(DBHandler.DB_COLUMN_NOTES);
        while (cursor.moveToNext()) {
            String nameValue = cursor.getString(nameIndex);
            String phoneValue = cursor.getString(phoneIndex);
            String notesValue = cursor.getString(notesIndex);
            textViewEntries.append(String.format("\n%s\n%s\n%s\n",
                    nameValue, phoneValue, notesValue));
        }
        cursor.close();
    }

    /**
     * Adds entry to table
     */
    private void databaseAddEntry() {
        // Read entry values
        ContentValues values = new ContentValues();
        values.put(DBHandler.DB_COLUMN_NAME, editTextName.getText().toString());
        values.put(DBHandler.DB_COLUMN_PHONE, editTextPhone.getText().toString());
        values.put(DBHandler.DB_COLUMN_NOTES, editTextNotes.getText().toString());

        // Add to database
        dbHandler.getWritableDatabase().insert(DBHandler.DB_TABLE_NAME, null, values);

        // Show entries (update)
        databaseShow();
    }

    /**
     * Deletes table entry by Name
     */
    private void databaseDeleteEntry() {
        // Delete rows with selected name
        String selection = DBHandler.DB_COLUMN_NAME + "=?";
        dbHandler.getWritableDatabase().delete(DBHandler.DB_TABLE_NAME,
                selection,
                new String[] {editTextName.getText().toString()});

        // Show entries (update)
        databaseShow();
    }

    /**
     * Removes entire table
     */
    private void databaseClear() {
        // Delete table
        dbHandler.getWritableDatabase().delete(DBHandler.DB_TABLE_NAME,
                null, null);

        // Show entries (update)
        databaseShow();
    }
}
