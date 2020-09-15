package com.example.ibtikarandroidtask

import android.app.Application
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.ibtikarandroidtask.data.FakeAppDataManager
import com.example.ibtikarandroidtask.data.local.db.FakeDbRepository
import com.example.ibtikarandroidtask.data.local.prefs.FakePreferenceRepository
import com.example.ibtikarandroidtask.data.model.api.ImagesResponse
import com.example.ibtikarandroidtask.data.model.api.PopularResponse
import com.example.ibtikarandroidtask.data.remote.FakeApiRepository
import com.example.ibtikarandroidtask.ui.main.popular.PopularFragment
import com.example.ibtikarandroidtask.ui.main.popular.PopularViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@MediumTest
class PopularListFragmentTest {
    @Inject
    lateinit var factory: ViewModelProviderFactory
    private lateinit var appContext: Application

    @Before
    fun before() {
        appContext = ApplicationProvider.getApplicationContext()

        val popularDataSource = FakeAppDataManager(
            FakeApiRepository(
                createPopularTestData(), ImagesResponse()
            ),
            FakeDbRepository(),
            FakePreferenceRepository()
        )

        val popularViewModel = PopularViewModel(
            appContext, popularDataSource
        )
    }

    private fun createPopularTestData(): PopularResponse {
        val list = ArrayList<com.example.ibtikarandroidtask.data.model.api.Result>()
        list.add(
            com.example.ibtikarandroidtask.data.model.api.Result(
                122503,
                "Liu Yifei",
                "Acting",
                "/cL6JccAYqiZQEAIEFObEUC9LTt7.jpg"
            )
        )
        list.add(
            com.example.ibtikarandroidtask.data.model.api.Result(
                990393,
                "Erin Moriarty",
                "Acting",
                "/oioBLC6lD6CfYGjDrs8iO6iH4gS.jpg"
            )
        )
        list.add(
            com.example.ibtikarandroidtask.data.model.api.Result(
                287,
                "Brad Pitt",
                "Acting",
                "/cckcYc2v0yh1tc9QjRelptcOBko.jpg"
            )
        )
        return PopularResponse(list)
    }

    @Test
    fun popularList_DisplayedOnUI_DataAvailable() {
        launchFragmentInContainer<PopularFragment>(null, R.style.AppTheme)
        Espresso.onView(withId(R.id.popular_recycler_view))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.progress_bar))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
    }
}