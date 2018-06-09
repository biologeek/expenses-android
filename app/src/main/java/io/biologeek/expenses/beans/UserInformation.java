package io.biologeek.expenses.beans;

/**
 * Created by xavier on 07/06/18.
 */

public class UserInformation {

    public static String TABLE_NAME = "user_information";

    public static String ID = "id";
    public static String NAME = "name";
    public static String SURNAME = "surname";
    public static String TOKEN = "token";

    private int id;
    private String name;
    private String surname;
    private String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
