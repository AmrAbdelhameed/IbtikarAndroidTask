package com.example.popularpeople.presentation.main.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.popularpeople.databinding.ItemFavoritesEmptyViewBinding
import com.example.popularpeople.databinding.ItemFavoritesViewBinding
import com.example.popularpeople.domain.dto.db.Popular
import com.example.popularpeople.presentation.base.BaseItemListener
import com.example.popularpeople.presentation.base.BaseRecyclerViewAdapter
import com.example.popularpeople.presentation.base.BaseViewHolder
import com.example.popularpeople.utils.AppConstants.VIEW_TYPE_NORMAL

class FavoritesAdapter(items: MutableList<Popular>, itemListener: FavoritesItemViewModelListener) :
    BaseRecyclerViewAdapter<Popular>(items, itemListener) {

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
            mBinding.popular = popular
            mBinding.item = FavoritesItemView { itemListener.onItemClick(popular) }
            mBinding.executePendingBindings()
        }
    }

    inner class EmptyViewHolder(private val mBinding: ItemFavoritesEmptyViewBinding) :
        BaseViewHolder(mBinding.root) {
        override fun onBind(position: Int) {
            mBinding.executePendingBindings()
        }
    }
}

interface FavoritesItemViewModelListener : BaseItemListener<Popular> {
    override fun onRetryClick() {}
}