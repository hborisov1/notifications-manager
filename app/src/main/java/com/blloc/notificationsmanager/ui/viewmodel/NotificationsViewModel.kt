package com.blloc.notificationsmanager.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.blloc.notificationsmanager.database.model.NotificationData
import com.blloc.notificationsmanager.repository.NotificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    repo: NotificationRepository
) : ViewModel() {

    val screenState = MutableLiveData<ScreenState>()

    val notifications: LiveData<List<NotificationData>> = repo.allNotifications.asLiveData()

    val activeNotifications: LiveData<List<NotificationData>> =
        repo.activeNotifications.asLiveData()

    fun determineViewState(isPermissionGranted: Boolean, isActiveChipChecked: Boolean) =
        screenState.postValue(
            when {
                !isPermissionGranted -> ScreenState.PERMISSIONS_NOT_GRANTED
                isPermissionGranted && isActiveChipChecked && (activeNotifications.value?.isEmpty() == true) -> ScreenState.EMPTY_STATE
                isPermissionGranted && isActiveChipChecked && (activeNotifications.value?.isEmpty() == false) -> ScreenState.ACTIVE_NOTIFICATIONS_STATE
                isPermissionGranted && !isActiveChipChecked && (notifications.value?.isEmpty() == true) -> ScreenState.EMPTY_STATE
                isPermissionGranted && !isActiveChipChecked && (notifications.value?.isEmpty() == false) -> ScreenState.ALL_NOTIFICATIONS_STATE
                else -> ScreenState.PERMISSIONS_NOT_GRANTED
            }
        )

    enum class ScreenState {
        PERMISSIONS_NOT_GRANTED,
        EMPTY_STATE,
        ALL_NOTIFICATIONS_STATE,
        ACTIVE_NOTIFICATIONS_STATE
    }

}