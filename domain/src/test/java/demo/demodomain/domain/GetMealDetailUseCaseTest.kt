package demo.demodomain.domain

import demo.demodomain.model.MealItemDetails
import demo.demodomain.repository.FindYourMealRepository
import demo.demodomain.use_case.GetMealDetailUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class GetMealDetailUseCaseTest{
    private val findYourMealRepository = mockk<FindYourMealRepository>()

    private fun getMealDetailUseCase(): GetMealDetailUseCase = GetMealDetailUseCase(
        findYourMealRepository
    )

    @Test
    fun testMealItemList() = runBlocking {
        val dummyMealDetail = getDummyMealDetail()
        coEvery {
            findYourMealRepository.getMealDetails("1234")
        } returns dummyMealDetail

        var mealDetail: MealItemDetails? = null
        val output = getMealDetailUseCase().invoke("1234")
        output.collect{
            mealDetail = it.data
        }
        assertEquals(
            dummyMealDetail,
            mealDetail
        )

    }

}