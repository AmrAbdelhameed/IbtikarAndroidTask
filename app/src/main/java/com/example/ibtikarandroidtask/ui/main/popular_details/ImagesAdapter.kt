package com.example.ibtikarandroidtask.ui.main.popular_details

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.ibtikarandroidtask.databinding.ItemImageViewBinding
import com.example.ibtikarandroidtask.ui.base.BaseRecyclerViewAdapter
import com.example.ibtikarandroidtask.ui.base.BaseViewHolder
import com.example.ibtikarandroidtask.ui.main.popular_details.ImageItemViewModel.ImageItemViewModelListener

class ImagesAdapter(
    items: MutableList<ImagesDataItem>,
    listener: ImageItemViewModelListener
) : BaseRecyclerViewAdapter<ImagesDataItem>(items, listener) {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ImagesViewHolder(
            ItemImageViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    inner class ImagesViewHolder(private val mBinding: ItemImageViewBinding) :
        BaseViewHolder(mBinding.root) {
        override fun onBind(position: Int) {
            val popular = items[position]
            mBinding.viewModel = ImageItemViewModel(popular) { itemListener.onItemClick(popular) }
            mBinding.executePendingBindings()
        }
    }
}