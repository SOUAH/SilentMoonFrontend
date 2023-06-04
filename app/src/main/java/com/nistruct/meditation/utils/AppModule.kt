package com.nistruct.meditation.utils

import android.content.Context
import com.nistruct.meditation.BuildConfig
import com.nistruct.meditation.data.AppDataStore.DataStoreInterface
import com.nistruct.meditation.data.repo.ApiImpl
import com.nistruct.meditation.data.repo.ApiInteractor
import com.nistruct.meditation.data.repo.DataStoreRepository
import com.nistruct.meditation.data.repo.UserRepository
import com.nistruct.meditation.retrofit.MeditationAPI
import com.nistruct.meditation.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDataStore(
        @ApplicationContext context: Context
    ): DataStoreInterface = DataStoreRepository(context)

    @Singleton
    @Provides
    fun provideSignUpRepository(
        apiInteracor: ApiInteractor,
        dataStore: DataStoreInterface,
        notificationHelper: NotificationHelper
    ): UserRepository {
        return UserRepository(apiInteracor, dataStore, notificationHelper)
    }

    @Singleton
    @Provides
    fun provideUserApi(
        @Named("retrofit")
        retrofit: Retrofit
    ): MeditationAPI = retrofit.create(MeditationAPI::class.java)

    @Singleton
    @Provides
    fun provideApiInteractor(
        userAPI: MeditationAPI
    ): ApiInteractor {
        return ApiImpl(userAPI)
    }


    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    } else {
        OkHttpClient
            .Builder()
            .build()
    }


    @Singleton
    @Provides
    @Named("retrofit")
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideNotificationHelper(
        @ApplicationContext context: Context
    ): NotificationHelper = NotificationHelper(context)
}