package com.example.popularpeople.presentation.main.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.popularpeople.databinding.ItemPopularEmptyViewBinding
import com.example.popularpeople.databinding.ItemPopularViewBinding
import com.example.popularpeople.presentation.base.BaseItemListener
import com.example.popularpeople.presentation.base.BaseRecyclerViewAdapter
import com.example.popularpeople.presentation.base.BaseViewHolder
import com.example.popularpeople.utils.AppConstants.VIEW_TYPE_NORMAL

class PopularAdapter(
    items: MutableList<PopularDataItem>,
    itemListener: PopularItemViewModelListener
) : BaseRecyclerViewAdapter<PopularDataItem>(items, itemListener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_NORMAL -> {
                PopularViewHolder(
                    ItemPopularViewBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
            }
            else -> {
                EmptyViewHolder(
                    ItemPopularEmptyViewBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
            }
        }
    }

    inner class PopularViewHolder(private val mBinding: ItemPopularViewBinding) :
        BaseViewHolder(mBinding.root) {
        override fun onBind(position: Int) {
            val popularDataItem = items[position]
            mBinding.popularDataItem = popularDataItem
            mBinding.item = PopularItemView { itemListener.onItemClick(popularDataItem) }
            mBinding.executePendingBindings()
        }
    }

    inner class EmptyViewHolder(private val mBinding: ItemPopularEmptyViewBinding) :
        BaseViewHolder(mBinding.root) {
        override fun onBind(position: Int) {
            mBinding.item = PopularEmptyItemView { itemListener.onRetryClick() }
            mBinding.executePendingBindings()
        }
    }
}

interface PopularItemViewModelListener : BaseItemListener<PopularDataItem>