package io.biologeek.expenses.repositories;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import java.util.List;

import io.biologeek.expenses.api.beans.Operation;
import io.biologeek.expenses.api.beans.User;
import io.biologeek.expenses.beans.UserInformation;
import io.biologeek.expenses.config.DatabaseHelper;

/**
 * Created by xavier on 07/06/18.
 */

public class UserInformationRepository implements CrudRepository<UserInformation, Integer>{

    public static String TABLE_NAME = "user_information";

    private SQLiteDatabase db;

    public UserInformationRepository(Context ctx){
        this.db = DatabaseHelper.getInstance(ctx).getWritableDatabase();
    }
    protected UserInformation toObject(Cursor cursor) {
        UserInformation res = new UserInformation();
        res.setId(cursor.getInt(0));
        res.setName(cursor.getString(1));
        res.setSurname(cursor.getString(2));
        res.setToken(cursor.getString(3));

        return null;
    }

    @Override
    public UserInformation get(Integer id) {
        String query = "SELECT * FROM "+ TABLE_NAME
                +" WHERE id = "+id;
        Cursor cursor = db.rawQuery(query, null);
        return toObject(cursor);
    }

    @Override
    public List<UserInformation> getAll() {
        return null;
    }

    @Override
    public UserInformation save(UserInformation toSave) {
        return null;
    }

    @Override
    public UserInformation update(UserInformation toSave) {
        return null;
    }
}
