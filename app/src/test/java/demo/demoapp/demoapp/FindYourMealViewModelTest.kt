package demo.demoapp.demoapp

import io.mockk.coEvery
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import app.cash.turbine.test
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test

import demo.demoapp.presentation.find_meal.FindYourMealState
import demo.demoapp.presentation.find_meal.FindYourMealViewModel
import demo.democommon.Resource
import demo.demodomain.model.Meal
import demo.demodomain.use_case.GetMealItemListUseCase

class FindYourMealViewModelTest {

    @get:Rule
    val coroutinesTestRule = MockMainDispatcherTestRule()

    private val getMealItemListUseCase = mockk<GetMealItemListUseCase>()

    private fun findYourViewModel(): FindYourMealViewModel = FindYourMealViewModel(
        getMealItemListUseCase
    )

    @Test
    fun testLoading() = runTest(coroutinesTestRule.testDispatcher) {

        val result : Flow<Resource<List<Meal>>> = flow {
            emit(Resource.Loading())
        }
        coEvery { getMealItemListUseCase.invoke("") } returns result

        findYourViewModel().findYourMealList.test{
            assertEquals(
                awaitItem(),
                FindYourMealState(null,"", findYourViewModel().findYourMealList.value.isLoading)
            )
        }
    }

    @Test
    fun testError() = runTest(coroutinesTestRule.testDispatcher) {

        val result : Flow<Resource<List<Meal>>> = flow {
            emit(Resource.Error("unknown error"))
        }
        coEvery { getMealItemListUseCase.invoke("") } returns result

        findYourViewModel().findYourMealList.test{
            assertEquals(
                awaitItem(),
                FindYourMealState(null, findYourViewModel().findYourMealList.value.error,false)
            )
        }
    }

    @Test
    fun testSuccess() = runTest(coroutinesTestRule.testDispatcher) {
        val result : Flow<Resource<List<Meal>>> = flow {
            emit(Resource.Success(data = listOf(Meal("52920", "Chicken","https://www.themealdb.com/images/media/meals/wyxwsp1486979827.jpg"))))
        }
        coEvery { getMealItemListUseCase.invoke("Chicken") } returns result
        findYourViewModel().findYourMealList.test{
            assertEquals(
                awaitItem(),
                FindYourMealState( findYourViewModel().findYourMealList.value.data,"",false)
            )
        }
    }

}