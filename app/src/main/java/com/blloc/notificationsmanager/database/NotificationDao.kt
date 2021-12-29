package com.blloc.notificationsmanager.database

import androidx.room.*
import com.blloc.notificationsmanager.database.model.NotificationData
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDao {

    @Query("SELECT * FROM notification_data ORDER BY timestamp DESC LIMIT 20")
    fun getAll(): Flow<List<NotificationData>>

    @Query("SELECT * FROM notification_data WHERE isActive=1 ORDER BY timestamp DESC LIMIT 20")
    fun getActive(): Flow<List<NotificationData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notificationData: NotificationData)

    @Update
    suspend fun update(notificationData: NotificationData)

}