package com.example.ibtikarandroidtask.ui.main.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.ibtikarandroidtask.databinding.ItemPopularEmptyViewBinding
import com.example.ibtikarandroidtask.databinding.ItemPopularViewBinding
import com.example.ibtikarandroidtask.ui.base.BaseRecyclerViewAdapter
import com.example.ibtikarandroidtask.ui.base.BaseViewHolder
import com.example.ibtikarandroidtask.ui.main.popular.PopularItemViewModel.PopularItemViewModelListener
import com.example.ibtikarandroidtask.utils.AppConstants.VIEW_TYPE_EMPTY
import com.example.ibtikarandroidtask.utils.AppConstants.VIEW_TYPE_NORMAL

class PopularAdapter(items: MutableList<PopularDataItem>, listener: PopularItemViewModelListener) :
    BaseRecyclerViewAdapter<PopularDataItem>(items, listener) {

    override fun getItemCount(): Int {
        return if (items.size > 0) items.size else 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (items.isNotEmpty()) VIEW_TYPE_NORMAL else VIEW_TYPE_EMPTY
    }

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
            val popular = items[position]
            mBinding.viewModel = PopularItemViewModel(popular) { itemListener.onItemClick(popular) }
            mBinding.executePendingBindings()
        }
    }

    inner class EmptyViewHolder(private val mBinding: ItemPopularEmptyViewBinding) :
        BaseViewHolder(mBinding.root) {
        override fun onBind(position: Int) {
            mBinding.viewModel = PopularEmptyItemViewModel { itemListener.onRetryClick() }
            mBinding.executePendingBindings()
        }
    }

}