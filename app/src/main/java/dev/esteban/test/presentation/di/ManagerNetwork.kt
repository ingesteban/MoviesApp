package dev.esteban.test.presentation.di

import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.squareup.moshi.Moshi
import dev.esteban.test.R
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Esteban Barrios on 14/05/2019.
 */
class ManagerNetwork(private val context: Context) {


    /**
     * Retorna la instancia de retrofit utilizada para crear un endpoint que apunte a The movie
     */
    fun getTheMovieRetrofit(): Retrofit{
        //Interceptor
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val original = chain.request()
                val originalHttpUrl = original.url()
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", context.getString(R.string.api_key_the_movie))
                    .build()
                val requestBuilder = original.newBuilder()
                    .url(url)
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .build()

        val moshi = Moshi.Builder().build()

        return Retrofit.Builder()
            .baseUrl(context.resources.getString(R.string.base_url_the_movie))
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}