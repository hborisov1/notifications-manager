package com.blloc.notificationsmanager.ui.adapter

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible")
fun isVisible(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}