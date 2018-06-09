package io.biologeek.expenses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.Callable;

import io.biologeek.expenses.api.AbstractAPI;
import io.biologeek.expenses.api.UserAPI;
import io.biologeek.expenses.api.beans.AuthenticationActionBean;
import io.biologeek.expenses.api.beans.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    private UserAPI userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addListenerToSubmitButton();
    }



    public void addListenerToSubmitButton(){
        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);

        final EditText login = findViewById(R.id.txtLogin);
        final EditText password =  findViewById( R.id.txtPassword);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = AbstractAPI.buildHttpClient();
                UserAPI api = retrofit.create(UserAPI.class);
                AuthenticationActionBean bean = new AuthenticationActionBean();
                bean.setLogin(login.getText().toString());
                bean.setPassword(password.getText().toString());
                Call<User> loginCall = api.login(bean);

                loginCall.enqueue(callback);

            }
        });
    }

    Callback<User> callback = new Callback<User>() {
        @Override
        public void onResponse(Call<User> call, Response<User> response) {
            if (response.isSuccessful() && response.body() != null) {
                Intent goToWelcome = new Intent(getApplicationContext(), OperationListActivity.class);

                startActivity(goToWelcome);
            } else {
                findViewById(R.id.errorMessage).setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onFailure(Call<User> call, Throwable t) {
            findViewById(R.id.errorMessage).setVisibility(View.VISIBLE);
        }
    };
}
