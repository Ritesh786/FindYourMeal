package transport.riteshdata.repository

import transport.riteshdata.model.MealItemsDTO
import transport.riteshdata.remote.FindYourMealAPi
import transport.riteshdom.repository.GetMealDetailsRepository

class GetMealDetailImpl(private val findYourMealAPi: FindYourMealAPi) : GetMealDetailsRepository {
    override suspend fun getMealDetails(id: String): MealItemsDTO {
      return findYourMealAPi.getMealDetails(id)
    }
}