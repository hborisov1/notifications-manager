package com.blloc.notificationsmanager.di

import android.content.Context
import androidx.room.Room
import com.blloc.notificationsmanager.database.AppDatabase
import com.blloc.notificationsmanager.database.NotificationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "notification-manager-database"
        ).build()

    @Provides
    fun provideNotificationDao(appDatabase: AppDatabase): NotificationDao =
        appDatabase.notificationDao()

}