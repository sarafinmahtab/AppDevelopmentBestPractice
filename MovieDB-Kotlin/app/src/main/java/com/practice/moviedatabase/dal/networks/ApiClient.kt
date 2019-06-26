package com.practice.moviedatabase.dal.networks

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.practice.moviedatabase.BuildConfig
import com.practice.moviedatabase.utilities.ServerConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {


    private var client: OkHttpClient? = null
    private var gsonConverterFactory: GsonConverterFactory? = null

    private val okHttpClient: OkHttpClient
        get() {

            if (client == null) {
                /*val interceptor = HttpLoggingInterceptor()
                interceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

                try {
                    val trustManagerFactory = TrustManagerFactory.getInstance(
                            TrustManagerFactory.getDefaultAlgorithm())

                    trustManagerFactory.init(null as KeyStore?)
                    val trustManagers = trustManagerFactory.trustManagers
                    if (trustManagers.size != 1 || trustManagers[0] !is X509TrustManager) {
                        throw IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers))
                    }
                    val trustManager = trustManagers[0] as X509TrustManager

                    val sslContext = SSLContext.getInstance("TLS")
                    sslContext.init(null, arrayOf<TrustManager>(trustManager), null)
                    val sslSocketFactory = sslContext.socketFactory

                    client = OkHttpClient.Builder().sslSocketFactory(sslSocketFactory, trustManager).addInterceptor(interceptor).connectTimeout(2, TimeUnit.MINUTES).readTimeout(2, TimeUnit.MINUTES).writeTimeout(2, TimeUnit.MINUTES).build()
                } catch (e: NoSuchAlgorithmException) {
                    e.printStackTrace()
                    client = OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(2, TimeUnit.MINUTES).readTimeout(2, TimeUnit.MINUTES).writeTimeout(2, TimeUnit.MINUTES).build()
                } catch (e: KeyStoreException) {
                    e.printStackTrace()
                    client = OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(2, TimeUnit.MINUTES).readTimeout(2, TimeUnit.MINUTES).writeTimeout(2, TimeUnit.MINUTES).build()
                } catch (e: KeyManagementException) {
                    e.printStackTrace()
                    client = OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(2, TimeUnit.MINUTES).readTimeout(2, TimeUnit.MINUTES).writeTimeout(2, TimeUnit.MINUTES).build()
                }*/

                val interceptor = HttpLoggingInterceptor()
                interceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                client = OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(1, TimeUnit.MINUTES).readTimeout(3, TimeUnit.MINUTES).writeTimeout(10, TimeUnit.MINUTES).build()

            }
            return client!!
        }



    fun getClient(url: String): Retrofit {
        val client = okHttpClient
        val factory = getGsonConverterFactory()
//        val nullFactory = NullOnEmptyConverterFactory()
        return Retrofit.Builder()
            .baseUrl(url)
//                .addConverterFactory(nullFactory)
            .addConverterFactory(factory)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
    }

    private fun getGsonConverterFactory(): GsonConverterFactory {
        if (gsonConverterFactory == null) {
            val gson = GsonBuilder()
                .disableHtmlEscaping()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create()
            gsonConverterFactory = GsonConverterFactory.create(gson)
        }
        return gsonConverterFactory!!
    }
}
