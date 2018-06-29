package io.biologeek.expenses.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

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
        if (cursor.getCount() == 0)
            return null;
        if (cursor.moveToFirst()){
            res.setId(cursor.getInt(cursor.getColumnIndex("id")));
            res.setName(cursor.getString(cursor.getColumnIndex("name")));
            res.setSurname(cursor.getString(cursor.getColumnIndex("surname")));
            res.setToken(cursor.getString(cursor.getColumnIndex("token")));

            return res;
        }
        return null;
    }

    @Override
    public UserInformation get(Integer id) {
        String query = "SELECT id, name, surname, token FROM "+ TABLE_NAME
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
        if (toSave.getId() != 0) {
            if (get(toSave.getId()) != null){
                return update(toSave);
            }
        }
        ContentValues values = new ContentValues();
        values.put("id", toSave.getId());
        values.put("name", toSave.getName());
        values.put("surname", toSave.getSurname());
        values.put("token", toSave.getToken());

        Long id = db.insertOrThrow(TABLE_NAME, null, values);

        return get(id.intValue());
    }

    @Override
    public UserInformation update(UserInformation toSave) {
        ContentValues values = new ContentValues();
        values.put("name", toSave.getName());
        values.put("surname", toSave.getSurname());
        values.put("token", toSave.getToken());
        int id = db.update(TABLE_NAME, values, "id = " + toSave.getId(), null);
        return get(id);
    }

    public UserInformation getCurrent() {
        String query = "SELECT id, name, surname, token FROM "+ TABLE_NAME
                +" LIMIT 1";
        Cursor cursor = db.rawQuery(query, null);
        return toObject(cursor);
    }
}
