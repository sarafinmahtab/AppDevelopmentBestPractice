package com.practice.moviedatabase.networks

import com.practice.moviedatabase.Urls
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient private constructor() {

    // Make the constructor of the class as 'private'.

    init {
        /*  Ensure that the Singleton class is Reflection Proof.
            A new instance of the Singleton can be created using Java Reflection API.
            In order to prevent that, we need to add a check to the constructor.
            If the constructor is already initialized, throw a run-time exception.  */
        if (retrofitInstance != null) {
            throw RuntimeException("Use getClient() method to get the single instance of this class.")
        }
    }

    companion object {

        private val BASE_URL = Urls.BASE_URL

        // Use volatile keyword to ensure that the Singleton is thread safe.
        @Volatile
        private var retrofitInstance: Retrofit? = null

        /* Use Double Check locking method. This means make the Singleton class
            in the synchronized block only if the instance is null. So, the synchronized
            block will be executed only when the 'instance' is null and prevent unnecessary
            synchronization once the instance variable is initialized.  */
        val client: Retrofit?
            get() {

                if (retrofitInstance == null) {

                    synchronized(ApiClient::class) {
                        if (retrofitInstance == null) {
                            retrofitInstance = Retrofit.Builder()
                                .baseUrl(BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build()

                        }
                    }
                }
                return retrofitInstance
            }
    }
}
