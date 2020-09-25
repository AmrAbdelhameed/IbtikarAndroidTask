package com.example.ibtikarandroidtask.presentation.main.popular_details

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.ibtikarandroidtask.BR
import com.example.ibtikarandroidtask.R
import com.example.ibtikarandroidtask.ViewModelProviderFactory
import com.example.ibtikarandroidtask.databinding.FragmentPopularDetailsBinding
import com.example.ibtikarandroidtask.presentation.base.BaseFragment
import com.example.ibtikarandroidtask.presentation.main.MainActivity
import com.example.ibtikarandroidtask.presentation.main.popular.PopularDataItem
import com.example.ibtikarandroidtask.utils.AppConstants
import com.example.ibtikarandroidtask.utils.ImageOverlayView
import com.stfalcon.frescoimageviewer.ImageViewer
import javax.inject.Inject

class PopularDetailsFragment :
    BaseFragment<FragmentPopularDetailsBinding, PopularDetailsViewModel>(),
    ImageItemViewModel.ImageItemViewModelListener {
    @Inject
    lateinit var factory: ViewModelProviderFactory
    private var popularDetailsViewModel: PopularDetailsViewModel? = null
    private var popularDataItem: PopularDataItem? = null
    private lateinit var imagesAdapter: ImagesAdapter

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_popular_details

    override val viewModel: PopularDetailsViewModel
        get() {
            popularDetailsViewModel = ViewModelProvider(this, factory).get(PopularDetailsViewModel::class.java)
            return popularDetailsViewModel as PopularDetailsViewModel
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imagesAdapter = ImagesAdapter(arrayListOf(), this)
        if (arguments != null) {
            popularDataItem = arguments?.getSerializable(AppConstants.POPULAR) as PopularDataItem?
            if (popularDataItem != null) { // To check if popular is favorite or not
                popularDataItem?.id?.let { popularDetailsViewModel?.getFavoriteById(it) }
                popularDataItem?.id?.let { viewModel.fetchImages(it) }
            }
        }
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        setUpToolbar()
        setPopular()
        setUpRecyclerView()
        getViewDataBinding().image.setOnClickListener {
            if (activity != null) {
                popularDataItem?.imageUrl?.let { it1 -> showImage(it1) }
            }
        }
    }

    private fun setUpRecyclerView() {
        getViewDataBinding().imagesRecyclerView.itemAnimator = DefaultItemAnimator()
        getViewDataBinding().imagesRecyclerView.adapter = imagesAdapter
    }

    private fun setPopular() {
        if (popularDataItem != null) {
            getViewDataBinding().popular = popularDataItem
        }
    }

    private fun setUpToolbar() {
        if (activity != null) {
            (activity as MainActivity).setSupportActionBar(getViewDataBinding().toolbar)
            val actionBar = (activity as MainActivity).supportActionBar
            actionBar?.setDisplayHomeAsUpEnabled(true)
            actionBar?.setDisplayShowHomeEnabled(true)
            actionBar?.setDisplayShowTitleEnabled(false)
        }
        getViewDataBinding().toolbar.setNavigationOnClickListener {
            if (activity != null) {
                activity?.onBackPressed()
            }
        }
    }

    override fun onItemClick(item: ImagesDataItem) {
        showImage(item.imageUrl)
    }

    private fun showImage(imageUrl: String) {
        val overlayView = ImageOverlayView(activity)
        ImageViewer.Builder<Any?>(activity, listOf(imageUrl))
            .setFormatter { imageUrl }
            .setImageChangeListener {
                overlayView.imageUrl = imageUrl
            }
            .setOverlayView(overlayView)
            .setStartPosition(0)
            .show()
    }
}