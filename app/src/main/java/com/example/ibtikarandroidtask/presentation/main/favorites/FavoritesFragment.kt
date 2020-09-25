package com.example.ibtikarandroidtask.presentation.main.favorites

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.ibtikarandroidtask.BR
import com.example.ibtikarandroidtask.R
import com.example.ibtikarandroidtask.ViewModelProviderFactory
import com.example.ibtikarandroidtask.databinding.FragmentFavoritesBinding
import com.example.ibtikarandroidtask.domain.dto.db.Popular
import com.example.ibtikarandroidtask.presentation.base.BaseFragment
import com.example.ibtikarandroidtask.presentation.base.NavigationCommand
import com.example.ibtikarandroidtask.presentation.main.MainActivity
import com.example.ibtikarandroidtask.presentation.main.popular.PopularDataItem
import com.example.ibtikarandroidtask.presentation.main.popular.PopularFragmentDirections
import javax.inject.Inject

class FavoritesFragment :
    BaseFragment<FragmentFavoritesBinding, FavoritesViewModel>(),
    FavoritesItemViewModelListener {
    @Inject
    lateinit var factory: ViewModelProviderFactory
    private lateinit var favoritesAdapter: FavoritesAdapter
    private var favoritesViewModel: FavoritesViewModel? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_favorites

    override val viewModel: FavoritesViewModel
        get() {
            favoritesViewModel = ViewModelProvider(this, factory).get(
                FavoritesViewModel::class.java
            )
            return favoritesViewModel as FavoritesViewModel
        }

    override fun onItemClick(item: Popular) {
        navigate(
            NavigationCommand.To(
                PopularFragmentDirections.toPopularDetailsFragment(
                    PopularDataItem(
                        item.id, item.name, item.knownForDepartment, item.imageUrl
                    )
                )
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoritesAdapter = FavoritesAdapter(arrayListOf(), this)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        if (activity != null) {
            (activity as MainActivity).setSupportActionBar(getViewDataBinding().toolbar)
            getViewDataBinding().toolbar.title = getString(R.string.favorites)
            (activity as MainActivity).supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
            }

        }
        getViewDataBinding().toolbar.setNavigationOnClickListener {
            if (activity != null) {
                activity?.onBackPressed()
            }
        }
        setHasOptionsMenu(true)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        getViewDataBinding().favoritesRecyclerView.itemAnimator = DefaultItemAnimator()
        getViewDataBinding().favoritesRecyclerView.adapter = favoritesAdapter
    }
}