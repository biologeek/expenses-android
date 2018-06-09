package io.biologeek.expenses.api;

import io.biologeek.expenses.api.beans.AuthenticationActionBean;
import io.biologeek.expenses.api.beans.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by xavier on 02/06/18.
 */

public interface UserAPI {

    String apiSubPath = "user";

    @POST(apiSubPath+"/login")
    Call<User> login(@Body AuthenticationActionBean login);
}
