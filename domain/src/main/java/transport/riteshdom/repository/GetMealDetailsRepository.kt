package transport.riteshdom.repository
import transport.riteshdom.model.MealItemDetails

interface GetMealDetailsRepository {
    suspend fun getMealDetails(id: String): MealItemDetails
}