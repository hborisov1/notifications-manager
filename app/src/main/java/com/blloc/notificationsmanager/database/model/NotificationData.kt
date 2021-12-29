package com.blloc.notificationsmanager.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notification_data")
data class NotificationData(
    @PrimaryKey val id: Int,
    @ColumnInfo val timestamp: Long,
    @ColumnInfo val title: String?,
    @ColumnInfo val description: String?,
    @ColumnInfo val isActive: Boolean,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB) val icon: ByteArray?
)