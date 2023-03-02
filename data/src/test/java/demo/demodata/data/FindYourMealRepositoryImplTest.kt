package demo.demodata.data

import demo.demodata.mapper.toDomainMeal
import demo.demodata.mapper.toDomainMealItemDetails
import demo.demodata.model.MealItemsDTO
import demo.demodata.remote.FindYourMealAPi
import demo.demodata.repository.FindYourMealRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Test

class FindYourMealRepositoryImplTest {
    private val findYourMealAPi = mockk<FindYourMealAPi>()
    private fun getMealListImpl(): FindYourMealRepositoryImpl = FindYourMealRepositoryImpl(
        findYourMealAPi
    )
    @Test
    fun testGetMealList() = runBlocking {
        val dummyMealItemsDTO = MealItemsDTO(listOf(getDummyMealDto()))
        coEvery {
            findYourMealAPi.getMealItemList("chicken")
        } returns dummyMealItemsDTO

        val mealItem  = dummyMealItemsDTO.meals[0].toDomainMeal()
        val mealList = getMealListImpl().getMealList("chicken")

        assertEquals(
            mealItem,
            mealList[0]
        )
    }
    @Test
    fun testGetMealDetail() = runBlocking {
        val dummyMealItemDTO = MealItemsDTO(listOf(getDummyMealDto()))
        coEvery {
            findYourMealAPi.getMealDetails("1234")
        } returns dummyMealItemDTO

        val dummyMealDetail = dummyMealItemDTO.meals[0].toDomainMealItemDetails()
        val mealDetail = getMealListImpl().getMealDetails("1234")

        assertEquals(
            dummyMealDetail,
            mealDetail
        )
    }

}