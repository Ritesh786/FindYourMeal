package transport.ritesh.demoapp

import android.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import transport.ritesh.demoapp.common.Resource
import transport.ritesh.demoapp.domain.use_case.GetMealItemListUseCase
import transport.ritesh.demoapp.presentation.find_meal.FindYourMealViewModel

class FindYourMealViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var getMealItemListUseCase : GetMealItemListUseCase
    private lateinit var findYourMealViewModel: FindYourMealViewModel
    val dispatcher = Dispatchers.Unconfined

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        findYourMealViewModel = FindYourMealViewModel(getMealItemListUseCase)
    }

    @Test
    fun test_GetMealItemListUseCase_Loading(){
        coEvery {
            getMealItemListUseCase("").onEach {
                when (it){
                    is Resource.Loading -> {
                     //   _findYourMealList.value = FindYourMealState(isLoading = true)
                    }
                    else -> {}
                }
            }
           findYourMealViewModel.findYourMealList
        }
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getFindYourMealList() {
    }

    @Test
    fun findYourMealList() {
    }
}