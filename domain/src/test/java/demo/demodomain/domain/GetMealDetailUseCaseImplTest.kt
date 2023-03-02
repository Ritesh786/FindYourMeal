package demo.demodomain.domain

import demo.demodomain.model.MealItemDetails
import demo.demodomain.repository.FindYourMealRepository
import demo.demodomain.use_case.GetMealDetailUseCaseImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class GetMealDetailUseCaseImplTest{
    private val findYourMealRepository = mockk<FindYourMealRepository>()

    private fun getMealDetailUseCase(): GetMealDetailUseCaseImpl = GetMealDetailUseCaseImpl(
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