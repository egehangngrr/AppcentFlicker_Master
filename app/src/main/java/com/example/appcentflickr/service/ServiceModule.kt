package com.ozzy.kukaf1.service

import com.example.appcentflickr.service.Repository
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Urls.BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun getApiCallInterface(retrofit: Retrofit): ApiCallInterface {
    return retrofit.create(ApiCallInterface::class.java)
}

fun provideHttpInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
}

fun getRequestHeader(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    val httpClient = OkHttpClient.Builder()

    httpClient.addInterceptor { chain ->
        val original = chain.request()
        val request = original.newBuilder()
            .build()
        chain.proceed(request)
    }.addInterceptor(httpLoggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
    return httpClient.build()
}


fun getRepository(apiCallInterface: ApiCallInterface): Repository {
    return Repository(apiCallInterface)
}

val serviceModule = module {

    single {
        provideRetrofit(get())
    }
    single {
        getApiCallInterface(get())
    }
    single {
        provideHttpInterceptor()
    }
    single {
        getRequestHeader(get())
    }
    single {
        getRepository(get())
    }
}
