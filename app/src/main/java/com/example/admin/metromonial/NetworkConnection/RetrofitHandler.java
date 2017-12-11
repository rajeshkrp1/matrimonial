package com.example.admin.metromonial.NetworkConnection;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitHandler {

    private static RetrofitHandler uniqInstance;
    private final  String BASE_URL = "http://www.wehyphens.com/matrimonial/webservice/";

    private FileUploadInterface apiInterface;

    public static synchronized RetrofitHandler getInstance() {
        if (uniqInstance == null) {
            uniqInstance = new RetrofitHandler();
        }
        return uniqInstance;
    }
    public static synchronized RetrofitHandler getNewInstanceOnLogin() {
        uniqInstance = new RetrofitHandler();
        return uniqInstance;
    }

    private void ApiClient() {
        try {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // set up log type
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(50, TimeUnit.SECONDS)
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build();
            apiInterface = retrofit.create(FileUploadInterface.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FileUploadInterface getApi() {

        if (apiInterface == null) {

            uniqInstance.ApiClient();

        }
        return apiInterface;
    }
   /* Interceptor header = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            String firebaseKey=AppUtils.readStringFromPref(HappyApplication.getmAppContext(), FIREBASE_KEY);
            Request.Builder builder = chain.request().newBuilder();
            builder.addHeader("Accept", "application/json");
            builder.addHeader(STR_API_KEY, API_KEY);
            if(firebaseKey==null){
                firebaseKey=" ";
            }
            builder.addHeader(STR_FIRE_KEY, firebaseKey);
            return chain.proceed(builder.build());
        }
    };*/
}
