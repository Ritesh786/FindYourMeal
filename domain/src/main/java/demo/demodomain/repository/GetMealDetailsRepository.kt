package demo.demodomain.repository
import demo.demodomain.model.MealItemDetails

interface GetMealDetailsRepository {
    suspend fun getMealDetails(id: String): MealItemDetails
}