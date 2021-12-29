package com.blloc.notificationsmanager.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blloc.notificationsmanager.database.model.NotificationData
import com.blloc.notificationsmanager.databinding.ItemNotificationBinding
import com.blloc.notificationsmanager.ui.utils.ByteArrayUtils

class NotificationsAdapter(
    private val notifications: List<NotificationData>,
    private val context: Context
) :
    RecyclerView.Adapter<NotificationsAdapter.NotificationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder =
        NotificationViewHolder(
            ItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            context
        )

    override fun onBindViewHolder(viewHolder: NotificationViewHolder, position: Int) =
        viewHolder.bind(notifications[position])

    override fun getItemCount() = notifications.size

    class NotificationViewHolder(
        private val binding: ItemNotificationBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(row: NotificationData) {
            binding.apply {
                tvNotificationTitle.text = row.title
                tvNotificationDescription.text = row.description
                row.icon?.let {
                    val imageDrawable = ByteArrayUtils().byteArrayToDrawable(it, context.resources)
                    ivNotificationIcon.setImageDrawable(imageDrawable)
                }
            }
        }

    }

}