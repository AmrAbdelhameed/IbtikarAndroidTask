package com.example.ibtikarandroidtask.presentation.base

import androidx.recyclerview.widget.RecyclerView
import com.example.ibtikarandroidtask.utils.AppConstants

abstract class BaseRecyclerViewAdapter<T>(
    val items: MutableList<T>,
    val itemListener: BaseItemListener<T>
) : RecyclerView.Adapter<BaseViewHolder>() {
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun addItems(items: List<T>?) {
        items?.let { this.items.addAll(it) }
        notifyDataSetChanged()
    }

    fun clearItems() {
        items.clear()
    }

    override fun getItemCount(): Int {
        return if (items.size > 0) items.size else 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (items.isNotEmpty()) AppConstants.VIEW_TYPE_NORMAL else AppConstants.VIEW_TYPE_EMPTY
    }
}