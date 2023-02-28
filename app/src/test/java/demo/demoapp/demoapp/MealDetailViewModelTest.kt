package demo.demoapp.demoapp

import app.cash.turbine.test
import demo.demoapp.presentation.meal_detail.MealDetailState
import demo.demoapp.presentation.meal_detail.MealDetailViewModel
import demo.democommon.Resource
import demo.demodomain.model.MealItemDetails
import demo.demodomain.use_case.GetMealDetailUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
@OptIn(ExperimentalCoroutinesApi::class)
class MealDetailViewModelTest{
    @get:Rule
    val coroutinesTestRule = MockMainDispatcherTestRule()

    private val getMealDetailUseCase = mockk<GetMealDetailUseCase>()

    private fun mealDetailViewModel(): MealDetailViewModel = MealDetailViewModel(
        getMealDetailUseCase
    )

    @Test
    fun testLoading() = runTest(coroutinesTestRule.testDispatcher) {

        val result : Flow<Resource<MealItemDetails>> = flow {
            emit(Resource.Loading())
        }
        coEvery { getMealDetailUseCase.invoke("") } returns result

        mealDetailViewModel().mealDetails.test {
            assertEquals(
                awaitItem(),
                MealDetailState(null,"", mealDetailViewModel().mealDetails.value.isLoading)
            )
        }
    }

    @Test
    fun testError() = runTest(coroutinesTestRule.testDispatcher) {
        val result : Flow<Resource<MealItemDetails>> = flow {
            emit(Resource.Error("unknown Error"))
        }
        coEvery { getMealDetailUseCase.invoke("") } returns result

        mealDetailViewModel().mealDetails.test {
            assertEquals(
                awaitItem(),
                MealDetailState(null,mealDetailViewModel().mealDetails.value.error, false)
            )
        }

    }

    @Test
    fun testSuccess() = runTest(coroutinesTestRule.testDispatcher) {
        val result : Flow<Resource<MealItemDetails>> = flow {
            emit(Resource.Success(data = getDummyMealDetail()))
        }
        coEvery { getMealDetailUseCase.invoke("1234") } returns result

        mealDetailViewModel().mealDetails.test {
            assertEquals(
                awaitItem(),
                MealDetailState(mealDetailViewModel().mealDetails.value.data,"", false)
            )
        }

    }

}