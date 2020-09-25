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
import com.example.ibtikarandroidtask.domain.dto.api.PopularResponse
import com.example.ibtikarandroidtask.presentation.main.popular.PopularFragment
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
    }

    private fun createPopularTestData(): PopularResponse {
        val list = ArrayList<com.example.ibtikarandroidtask.domain.dto.api.Result>()
        list.add(
            com.example.ibtikarandroidtask.domain.dto.api.Result(
                122503,
                "Liu Yifei",
                "Acting",
                "/cL6JccAYqiZQEAIEFObEUC9LTt7.jpg"
            )
        )
        list.add(
            com.example.ibtikarandroidtask.domain.dto.api.Result(
                990393,
                "Erin Moriarty",
                "Acting",
                "/oioBLC6lD6CfYGjDrs8iO6iH4gS.jpg"
            )
        )
        list.add(
            com.example.ibtikarandroidtask.domain.dto.api.Result(
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