package com.blloc.notificationsmanager.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.blloc.notificationsmanager.R
import com.blloc.notificationsmanager.database.model.NotificationData
import com.blloc.notificationsmanager.databinding.ActivityMainBinding
import com.blloc.notificationsmanager.ui.adapter.NotificationsAdapter
import com.blloc.notificationsmanager.ui.utils.NotificationUtils
import com.blloc.notificationsmanager.ui.viewmodel.NotificationsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityMainBinding
    private val viewModel: NotificationsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        dataBinding.btnGoToSettings.setOnClickListener { openNotificationPermissions() }
        dataBinding.btnCreateNotification.setOnClickListener { NotificationUtils(this).createTestNotification() }
        dataBinding.cgFilters.setOnCheckedChangeListener { _, checkedId ->
            updateGroupsVisibility()
        }
        viewModel.notifications.observe(this, { list: List<NotificationData> ->
            dataBinding.rvNotifications.apply {
                adapter = NotificationsAdapter(list, context)
                layoutManager = LinearLayoutManager(context)
            }
            updateGroupsVisibility()
        })
        viewModel.activeNotifications.observe(this, { list: List<NotificationData> ->
            dataBinding.rvActiveNotifications.apply {
                adapter = NotificationsAdapter(list, context)
                layoutManager = LinearLayoutManager(context)
            }
            updateGroupsVisibility()
        })
        viewModel.screenState.observe(this, {
            dataBinding.screenState = it
        })
    }

    override fun onResume() {
        super.onResume()
        updateGroupsVisibility()
    }

    private fun openNotificationPermissions() {
        startActivity(Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
    }

    private fun isNotificationListenerEnabled(): Boolean {
        return NotificationManagerCompat.getEnabledListenerPackages(this).contains(packageName)
    }

    private fun updateGroupsVisibility() {
        val isNotificationListenerEnabled = isNotificationListenerEnabled()
        viewModel.determineViewState(
            isPermissionGranted = isNotificationListenerEnabled,
            isActiveChipChecked = R.id.chip_active == dataBinding.cgFilters.checkedChipId
        )
    }
}