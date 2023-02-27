package demo.demodata.data

import demo.demodata.model.MealItemsDTO
import demo.demodata.model.toDomainMeal
import demo.demodata.remote.FindYourMealAPi
import demo.demodata.repository.GetMealListImpl
import demo.demodomain.model.Meal
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Test

class GetMealListImplTest {
    private val findYourMealAPi = mockk<FindYourMealAPi>()
    private fun getMealListImpl(): GetMealListImpl = GetMealListImpl(
        findYourMealAPi
    )
    @Test
    fun testGetMealList() = runBlocking {
        val dummyMealItemsDTO = MealItemsDTO(listOf(getDummyMealDto()))
        coEvery {
            findYourMealAPi.getMealItemList("chicken")
        } returns dummyMealItemsDTO

        val mealItem  = Meal(dummyMealItemsDTO.meals[0].idMeal,dummyMealItemsDTO.meals[0].strMeal,dummyMealItemsDTO.meals[0].strMealThumb)
        val getMealItemDto = getMealListImpl().getMealList("chicken")

        assertEquals(
            mealItem,
            getMealItemDto[0]
        )


    }
}