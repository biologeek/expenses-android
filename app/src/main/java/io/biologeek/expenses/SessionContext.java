package io.biologeek.expenses;

import android.content.Context;

import io.biologeek.expenses.beans.UserInformation;

/**
 * Created by xavier on 16/06/18.
 */

public interface SessionContext {
    void setSessionInformation(UserInformation info);
    UserInformation getSessionInformation();
}
