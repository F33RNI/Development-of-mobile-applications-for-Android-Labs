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

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DBHandler extends SQLiteOpenHelper implements BaseColumns {
    // Database .db file name and version
    public static final String DB_FILE_NAME = "database.db";
    public static final int DB_VERSION = 1;

    // Database columns
    public static final String DB_TABLE_NAME = "people";
    public static final String DB_COLUMN_ID = "_id";
    public static final String DB_COLUMN_NAME = "name";
    public static final String DB_COLUMN_PHONE = "phone";
    public static final String DB_COLUMN_NOTES = "notes";

    public DBHandler(Context context) {
        super(context, DB_FILE_NAME, null, DB_VERSION);
    }

    /**
     * Creates table if not exists
     * @param db SQLiteDatabase object
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSQL =
                String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT);",
                        DB_TABLE_NAME,
                        DB_COLUMN_ID,
                        DB_COLUMN_NAME,
                        DB_COLUMN_PHONE,
                        DB_COLUMN_NOTES);
        db.execSQL(createTableSQL);
    }

    /**
     * Deletes current table if DB_VERSION changed and creates new one
     * @param db SQLiteDatabase object
     * @param oldVersion Old DB_VERSION
     * @param newVersion New DB_VERSION
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Delete old table
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", DB_TABLE_NAME));

        // Create new one
        onCreate(db);
    }
}
