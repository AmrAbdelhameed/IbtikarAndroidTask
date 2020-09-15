package com.example.ibtikarandroidtask.network

import com.example.ibtikarandroidtask.BuildConfig
import com.example.ibtikarandroidtask.data.model.Result
import com.example.ibtikarandroidtask.data.remote.ApiRepository
import com.example.ibtikarandroidtask.data.remote.network.ApiService
import com.example.ibtikarandroidtask.utils.TestUtils
import com.example.ibtikarandroidtask.utils.jsonResponseFileName
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.`is`
import org.junit.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors

@ExperimentalCoroutinesApi
class PopularRepositoryTest {
    @get:Rule
    val mockWebServer = MockWebServer()
    private val mainThreadSurrogate = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    private lateinit var popularRepository: ApiRepository

    private val mockRetrofit by lazy {
        Retrofit.Builder()
            .baseUrl(mockWebServer.url(BuildConfig.BASE_URL))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val mockPopularService: ApiService by lazy {
        mockRetrofit.create(ApiService::class.java)
    }

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        popularRepository = ApiRepository(
            mockPopularService,
            BuildConfig.API_KEY
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun getpopular_loadedSuccessfully() {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestUtils.getJson(jsonResponseFileName))
                .setResponseCode(200)
        )
        runBlocking {
            launch(Dispatchers.Main) {
                when (val result = popularRepository.getPopular(1)) {
                    is Result.Success -> {
                        Assert.assertThat(result.data, `is`(TestUtils.getPopularTestObject()))
                    }
                    is Result.Error -> {
                        Assert.fail(result.message)
                    }
                }
            }
        }
    }
}