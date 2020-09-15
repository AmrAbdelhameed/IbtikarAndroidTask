package com.example.ibtikarandroidtask.popularlist

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.ibtikarandroidtask.BuildConfig
import com.example.ibtikarandroidtask.MainCoroutineRule
import com.example.ibtikarandroidtask.data.AppDataManager
import com.example.ibtikarandroidtask.data.local.db.AppDatabase
import com.example.ibtikarandroidtask.data.local.db.DbRepository
import com.example.ibtikarandroidtask.data.local.prefs.PreferencesRepository
import com.example.ibtikarandroidtask.data.remote.ApiRepository
import com.example.ibtikarandroidtask.data.remote.network.ApiService
import com.example.ibtikarandroidtask.ui.main.popular.PopularViewModel
import com.example.ibtikarandroidtask.utils.AppConstants
import com.example.ibtikarandroidtask.utils.LiveDataTestUtil
import com.example.ibtikarandroidtask.utils.TestUtils
import com.example.ibtikarandroidtask.utils.jsonResponseFileName
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors

@RunWith(RobolectricTestRunner::class)
@ExperimentalCoroutinesApi
class PopularListViewModelTest {
    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockWebServer = MockWebServer()

    private val mainThreadSurrogate = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

    // Set the main coroutines dispatcher for unit testing.
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()
    private lateinit var applicationContext: Application

    private val mockRetrofit by lazy {
        Retrofit.Builder()
            .baseUrl(mockWebServer.url(BuildConfig.BASE_URL))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    private val mockApiService by lazy {
        mockRetrofit.create(ApiService::class.java)
    }

    private val mockDb by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, AppConstants.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    private val mockSharedPreferences by lazy {
        applicationContext.getSharedPreferences(AppConstants.PREF_NAME, Context.MODE_PRIVATE)
    }

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        applicationContext = ApplicationProvider.getApplicationContext()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun loadPopular_Success() {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestUtils.getJson(jsonResponseFileName))
                .setResponseCode(200)
        )

        val popularDataSource = AppDataManager(
            ApiRepository(mockApiService, BuildConfig.API_KEY),
            DbRepository(mockDb),
            PreferencesRepository(mockSharedPreferences)
        )
        // Pause dispatcher so we can verify initial values
        mainCoroutineRule.pauseDispatcher()

        val popularViewModel = PopularViewModel(
            applicationContext, popularDataSource
        )
        //validate loading started
        TestCase.assertEquals(true, LiveDataTestUtil.getValue(popularViewModel.isLoading))

        mainCoroutineRule.resumeDispatcher()

        TestCase.assertEquals(
            TestUtils.getPopularTestObject(),
            LiveDataTestUtil.getValue(popularViewModel.popularListLiveData)
        )

        TestCase.assertEquals(false, LiveDataTestUtil.getValue(popularViewModel.isLoading))
    }

    @Test
    fun loadPopular_Error() {
        mockWebServer.enqueue(
            MockResponse()
                .setBody("Error")
                .setResponseCode(403)
        )

        val popularDataSource = AppDataManager(
            ApiRepository(mockApiService, BuildConfig.API_KEY),
            DbRepository(mockDb),
            PreferencesRepository(mockSharedPreferences)
        )

        // Pause dispatcher so we can verify initial values
        mainCoroutineRule.pauseDispatcher()

        val popularViewModel = PopularViewModel(
            applicationContext, popularDataSource
        )

        //validate loading started
        TestCase.assertEquals(true, LiveDataTestUtil.getValue(popularViewModel.isLoading))

        mainCoroutineRule.resumeDispatcher()

        TestCase.assertEquals(false, LiveDataTestUtil.getValue(popularViewModel.isLoading))
    }
}