package com.practice.moviedb.networks;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "https://api.themoviedb.org/3/movie/";

    // Use volatile keyword to ensure that the Singleton is thread safe.
    private static volatile Retrofit retrofitInstance = null;

    // Make the constructor of the class as 'private'.
    private ApiClient() {

        /*  Step 3: Ensure that the Singleton class is Reflection Proof.
            A new instance of the Singleton can be created using Java Reflection API.
            In order to prevent that, we need to add a check to the constructor.
            If the constructor is already initialized, throw a run-time exception.  */
        if (retrofitInstance != null){
            throw new RuntimeException("Use getClient() method to get the single instance of this class.");
        }
    }

    public static Retrofit getClient() {

        if (retrofitInstance == null) {

            /* Use Double Check locking method. This means make the Singleton class
            in the synchronized block only if the instance is null. So, the synchronized
            block will be executed only when the 'instance' is null and prevent unnecessary
            synchronization once the instance variable is initialized.  */

            synchronized (ApiClient.class) {
                if (retrofitInstance == null) {
                    retrofitInstance = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                }
            }
        }
        return retrofitInstance;
    }
}
