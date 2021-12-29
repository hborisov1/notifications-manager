package com.blloc.notificationsmanager.service

import android.app.Notification.EXTRA_TEXT
import android.app.Notification.EXTRA_TITLE
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.blloc.notificationsmanager.database.model.NotificationData
import com.blloc.notificationsmanager.repository.NotificationRepository
import com.blloc.notificationsmanager.ui.utils.ByteArrayUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class NotificationListener : NotificationListenerService() {

    @Inject
    lateinit var repo: NotificationRepository

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        sbn?.let {
            repo.insert(it.toNotificationData(isActive = true))
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        super.onNotificationRemoved(sbn)
        sbn?.let {
            repo.update(it.toNotificationData(isActive = false))
        }
    }

    private fun getIconBytes(sbn: StatusBarNotification): ByteArray {
        val packageName = sbn.packageName
        val otherAppContext =
            applicationContext.createPackageContext(packageName, CONTEXT_IGNORE_SECURITY)
        val iconDrawable = otherAppContext.getDrawable(sbn.notification.smallIcon.resId)
        return iconDrawable?.let { ByteArrayUtils().drawableToByteArray(it) } ?: ByteArray(0)
    }

    private fun StatusBarNotification.toNotificationData(isActive: Boolean): NotificationData {
        val title = notification.extras.getCharSequence(EXTRA_TITLE).toString()
        val content = notification.extras.getCharSequence(EXTRA_TEXT).toString()
        val timestamp = notification.`when`
        val iconBytes = getIconBytes(this)
        return NotificationData(
            id = id,
            timestamp = timestamp,
            title = title,
            description = content,
            icon = iconBytes,
            isActive = isActive
        )
    }

}