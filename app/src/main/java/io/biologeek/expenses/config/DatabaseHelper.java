package io.biologeek.expenses.config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

/**
 * Created by xavier on 07/06/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    private static DatabaseHelper instance;
    private static final String CREATE_USER_INFORMATION_TABLE = "CREATE TABLE user_information (" +
            "id INTEGER PRIMARY KEY," +
            "name VARCHAR 50," +
            "surname VARCHAR 50," +
            "token VARCHAR 100" +
            ");";

    private static String CREATE_ = "CREATE TABLE user_information (" +
            ")";

    private DatabaseHelper(Context context){
        super(context, "expenses", null, 1);
    }

    public static DatabaseHelper getInstance(Context ctx){
        if (instance == null)
            instance = new DatabaseHelper(ctx);
        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_INFORMATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
