package demo.demodomain.domain

import demo.demodomain.repository.FindYourMealRepository
import demo.demodomain.use_case.GetMealItemListUseCase
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import demo.demodomain.model.Meal
import io.mockk.coEvery
import junit.framework.TestCase
import kotlinx.coroutines.flow.first
import org.junit.Rule
import org.junit.Test

class GetMealItemListUseCaseTest {
    private val findYourMealRepository = mockk<FindYourMealRepository>()

    private fun getMealItemListUseCase(): GetMealItemListUseCase = GetMealItemListUseCase(
        findYourMealRepository
    )

    @Test
    fun testMealItemList() = runBlocking {
        val mealList = listOf(Meal("52920", "Chicken","https://www.themealdb.com/images/media/meals/wyxwsp1486979827.jpg"))
        coEvery {
            findYourMealRepository.getMealList("potato")
        } returns mealList

        var meal: Meal? = null
        val output = getMealItemListUseCase().invoke("potato")
        output.collect{
            meal = it.data?.first()
        }
        assertEquals(
            mealList[0],
            meal
        )

    }

}