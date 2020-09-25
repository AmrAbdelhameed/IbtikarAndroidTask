package com.example.ibtikarandroidtask.presentation.main.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.ibtikarandroidtask.domain.dto.db.Popular
import com.example.ibtikarandroidtask.databinding.ItemFavoritesEmptyViewBinding
import com.example.ibtikarandroidtask.databinding.ItemFavoritesViewBinding
import com.example.ibtikarandroidtask.presentation.base.BaseRecyclerViewAdapter
import com.example.ibtikarandroidtask.presentation.base.BaseViewHolder
import com.example.ibtikarandroidtask.presentation.main.favorites.FavoritesItemViewModel.FavoritesItemViewModelListener
import com.example.ibtikarandroidtask.utils.AppConstants.VIEW_TYPE_EMPTY
import com.example.ibtikarandroidtask.utils.AppConstants.VIEW_TYPE_NORMAL

class FavoritesAdapter(items: MutableList<Popular>, listener: FavoritesItemViewModelListener) :
    BaseRecyclerViewAdapter<Popular>(items, listener) {

    override fun getItemCount(): Int {
        return if (items.size > 0) items.size else 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (items.isNotEmpty()) VIEW_TYPE_NORMAL else VIEW_TYPE_EMPTY
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_NORMAL -> {
                FavoritesViewHolder(
                    ItemFavoritesViewBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
            }
            else -> {
                EmptyViewHolder(
                    ItemFavoritesEmptyViewBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
            }
        }
    }

    inner class FavoritesViewHolder(private val mBinding: ItemFavoritesViewBinding) :
        BaseViewHolder(mBinding.root) {
        override fun onBind(position: Int) {
            val popular = items[position]
            mBinding.viewModel =
                FavoritesItemViewModel(popular) { itemListener.onItemClick(popular) }
            mBinding.executePendingBindings()
        }
    }

    inner class EmptyViewHolder(private val mBinding: ItemFavoritesEmptyViewBinding) :
        BaseViewHolder(mBinding.root) {
        override fun onBind(position: Int) {
            mBinding.viewModel = FavoritesEmptyItemViewModel()
            mBinding.executePendingBindings()
        }
    }

}