package io.biologeek.expenses.api;

import android.content.Context;
import android.content.Intent;

import java.io.IOException;

import io.biologeek.expenses.OperationListActivity;
import io.biologeek.expenses.beans.UserInformation;
import io.biologeek.expenses.repositories.UserInformationRepository;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by xavier on 02/06/18.
 */
public class AbstractAPI {
    protected String apiUrl = "http://51.15.176.183:8090/expenses/";

    protected String apiSubPath = "";
    private boolean authenticated = false;

    protected String sessionToken;
    protected String userId;

    private Retrofit client;
    private Context ctx;
    private UserInformationRepository repository;

    public AbstractAPI(boolean authenticated){
        this.authenticated = authenticated;
    }
    /**
     * Creates an instance of Retrofit client if not instantiated yet
     * @return
     */
    public Retrofit buildHttpClient(Context ctxt){
        ctx = ctxt;
        this.repository = new UserInformationRepository(ctxt);
        UserInformation info = repository.getCurrent();
        if (info != null) {
            this.sessionToken = info.getToken();
            this.userId = String.valueOf(info.getId());
        }
        if (client == null)
            client = new Retrofit.Builder()
                .baseUrl(apiUrl+apiSubPath)
                .client(okHttp())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        return client;
    }

    /**
     * Adds an interceptor for requests
     * @return
     */
    protected OkHttpClient okHttp(){

        return new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder request = chain.request()
                        .newBuilder();
                        if (authenticated) {
                            if (sessionToken != null && userId != null) {
                                request.addHeader("Authorization", sessionToken)
                                        .addHeader("user", userId);
                            } else {
                                Intent goToLogin = new Intent(ctx, OperationListActivity.class);
                                ctx.startActivity(goToLogin);
                            }
                        }
                return chain.proceed(request.build());
            }
        }).build();
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
