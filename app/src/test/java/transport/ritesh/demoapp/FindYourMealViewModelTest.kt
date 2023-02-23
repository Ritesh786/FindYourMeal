package transport.ritesh.demoapp

import android.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import app.cash.turbine.test
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import transport.ritesh.demoapp.common.Resource
import transport.ritesh.demoapp.data.model.toDomainMeal
import transport.ritesh.demoapp.domain.model.Meal
import transport.ritesh.demoapp.domain.repository.FindYourMealRepository
import transport.ritesh.demoapp.domain.use_case.GetMealItemListUseCase
import transport.ritesh.demoapp.presentation.find_meal.FindYourMealState
import transport.ritesh.demoapp.presentation.find_meal.FindYourMealViewModel

class FindYourMealViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = MockMainDispatcherTestRule()

    private val getMealItemListUseCase = mockk<GetMealItemListUseCase>()

    private fun buildVM(): FindYourMealViewModel = FindYourMealViewModel(
        getMealItemListUseCase
    )

    @Test
    fun testLoading() = runTest(coroutinesTestRule.testDispatcher) {

        val result : Flow<Resource<List<Meal>>> = flow {
            emit(Resource.Loading())
        }
        coEvery { getMealItemListUseCase.invoke("") } returns result

        val vm = buildVM()
        // Assert
        vm.findYourMealList.test{
            assertEquals(
                awaitItem(),
                FindYourMealState(null,"",vm.findYourMealList.value.isLoading)
            )
        }
    }

    @Test
    fun testError() = runTest(coroutinesTestRule.testDispatcher) {

        val result : Flow<Resource<List<Meal>>> = flow {
            emit(Resource.Error("unknown error"))
        }
        coEvery { getMealItemListUseCase.invoke("") } returns result

        val vm = buildVM()
        // Assert
        vm.findYourMealList.test{
            assertEquals(
                awaitItem(),
                FindYourMealState(null,vm.findYourMealList.value.error,false)
            )
        }
    }

    @Test
    fun testSuccess() = runTest(coroutinesTestRule.testDispatcher) {
        val result : Flow<Resource<List<Meal>>> = flow {
            emit(Resource.Success(data = listOf(Meal("52920", "Chicken","https://www.themealdb.com/images/media/meals/wyxwsp1486979827.jpg"))))
        }
        coEvery { getMealItemListUseCase.invoke("Chicken") } returns result
        val vm = buildVM()
        // Assert
        vm.findYourMealList.test{
            assertEquals(
                awaitItem(),
                FindYourMealState(vm.findYourMealList.value.data,"",false)
            )
        }
    }

}