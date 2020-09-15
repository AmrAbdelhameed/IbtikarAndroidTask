package com.example.ibtikarandroidtask.ui.main.popular

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ibtikarandroidtask.BR
import com.example.ibtikarandroidtask.R
import com.example.ibtikarandroidtask.ViewModelProviderFactory
import com.example.ibtikarandroidtask.databinding.FragmentPopularBinding
import com.example.ibtikarandroidtask.ui.base.BaseFragment
import com.example.ibtikarandroidtask.ui.base.NavigationCommand
import com.example.ibtikarandroidtask.ui.main.MainActivity
import com.example.ibtikarandroidtask.ui.main.popular.PopularItemViewModel.PopularItemViewModelListener
import com.example.ibtikarandroidtask.utils.OnLoadMoreListener
import com.example.ibtikarandroidtask.utils.RecyclerViewLoadMoreScroll
import javax.inject.Inject

class PopularFragment : BaseFragment<FragmentPopularBinding, PopularViewModel>(),
    PopularItemViewModelListener {
    @Inject
    lateinit var factory: ViewModelProviderFactory
    private lateinit var popularAdapter: PopularAdapter
    private var popularViewModel: PopularViewModel? = null
    private lateinit var scrollListener: RecyclerViewLoadMoreScroll

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_popular

    override val viewModel: PopularViewModel
        get() {
            popularViewModel = ViewModelProvider(this, factory).get(PopularViewModel::class.java)
            return popularViewModel as PopularViewModel
        }

    override fun onRetryClick() {
        popularViewModel?.fetchPopular(viewModel.page)
    }

    override fun onItemClick(item: PopularDataItem) {
        viewModel.navigationCommand.postValue(
            NavigationCommand.To(
                PopularFragmentDirections.toPopularDetailsFragment(
                    item
                )
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        popularAdapter = PopularAdapter(arrayListOf(), this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        if (activity != null) (activity as MainActivity).setSupportActionBar(
            getViewDataBinding().toolbar
        )
        setHasOptionsMenu(true)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        getViewDataBinding().popularRecyclerView.itemAnimator = DefaultItemAnimator()
        getViewDataBinding().popularRecyclerView.adapter = popularAdapter
        scrollListener = RecyclerViewLoadMoreScroll(getViewDataBinding().popularRecyclerView.layoutManager as LinearLayoutManager)
        scrollListener.setOnLoadMoreListener(object : OnLoadMoreListener {
            override fun onLoadMore() {
                viewModel.page += 1
                viewModel.fetchPopular(viewModel.page)
            }
        })
        getViewDataBinding().popularRecyclerView.addOnScrollListener(scrollListener)
    }

    override fun onCreateOptionsMenu(
        menu: Menu,
        inflater: MenuInflater
    ) {
        inflater.inflate(R.menu.main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorites) {
            viewModel.navigationCommand.postValue(
                NavigationCommand.To(PopularFragmentDirections.toFavoritesFragment())
            )
        }
        return super.onOptionsItemSelected(item)
    }
}