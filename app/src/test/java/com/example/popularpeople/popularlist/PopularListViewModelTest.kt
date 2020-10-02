package com.example.popularpeople.popularlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.popularpeople.BuildConfig
import com.example.popularpeople.data.remote.ApiService
import com.example.popularpeople.domain.repository.PopularRepository
import com.example.popularpeople.presentation.main.popular.PopularViewModel
import com.example.popularpeople.utils.LiveDataTestUtil
import com.example.popularpeople.utils.MainCoroutinesRule
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class PopularListViewModelTest {
    private lateinit var popularViewModel: PopularViewModel
    private lateinit var popularRepository: PopularRepository

    @get:Rule
    val mockWebServer = MockWebServer()

    private val mockRetrofit by lazy {
        Retrofit.Builder()
            .baseUrl(mockWebServer.url(BuildConfig.BASE_URL))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val mockApiService by lazy {
        mockRetrofit.create(ApiService::class.java)
    }

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        popularRepository = PopularRepository(mockApiService, BuildConfig.API_KEY)
        popularViewModel = PopularViewModel(popularRepository)
    }

    @Test
    fun testPopularListLiveData() = runBlocking {
        val value = LiveDataTestUtil.getValue(popularViewModel.popularListLiveData)
        Assert.assertThat(value, (CoreMatchers.not(CoreMatchers.nullValue())))
        TestCase.assertEquals(false, LiveDataTestUtil.getValue(popularViewModel.isLoading))
    }
}