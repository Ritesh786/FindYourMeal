package transport.ritesh.demoapp.data.repository

import transport.ritesh.demoapp.data.model.MealItemsDTO
import transport.ritesh.demoapp.data.remote.FindYourMealAPi
import transport.ritesh.demoapp.domain.repository.GetMealDetailsRepository

class GetMealDetailImpl(private val findYourMealAPi: FindYourMealAPi) : GetMealDetailsRepository {
    override suspend fun getMealDetails(id: String): MealItemsDTO {
      return findYourMealAPi.getMealDetails(id)
    }
}