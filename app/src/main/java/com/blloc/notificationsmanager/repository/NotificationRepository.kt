package com.blloc.notificationsmanager.repository

import com.blloc.notificationsmanager.database.NotificationDao
import com.blloc.notificationsmanager.database.model.NotificationData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotificationRepository @Inject constructor(
    private val dao: NotificationDao
) {

    val allNotifications: Flow<List<NotificationData>> = dao.getAll()

    val activeNotifications: Flow<List<NotificationData>> = dao.getActive()

    fun insert(notificationData: NotificationData) {
        CoroutineScope(Job() + Dispatchers.IO).launch {
            dao.insert(notificationData)
        }
    }

    fun update(notificationData: NotificationData) {
        CoroutineScope(Job() + Dispatchers.IO).launch {
            dao.update(notificationData)
        }
    }
}