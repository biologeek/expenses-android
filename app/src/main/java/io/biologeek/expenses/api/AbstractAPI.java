package io.biologeek.expenses.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by xavier on 02/06/18.
 */

public abstract class AbstractAPI {
    protected static String apiUrl = "http://:8080/expenses/";

    protected static String apiSubPath = "";

    protected static String sessionToken;
    protected static String userId;

    private static Retrofit client;

    /**
     * Creates an instance of Retrofit client if not instantiated yet
     * @return
     */
    public static  Retrofit buildHttpClient(){
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
    protected static OkHttpClient okHttp(){
        return new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder request = chain.request()
                        .newBuilder();
                        if (sessionToken != null && userId != null)
                            request.addHeader("Authorization", sessionToken)
                                    .addHeader("user", userId);

                return chain.proceed(request.build());
            }
        }).build();
    }

}
