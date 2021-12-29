package com.blloc.notificationsmanager.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.blloc.notificationsmanager.database.model.NotificationData

@Database(entities = [NotificationData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notificationDao(): NotificationDao
}