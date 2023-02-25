package demo.demodata.repository

import demo.demodata.model.toDomainMealItemDetails
import demo.demodata.remote.FindYourMealAPi
import demo.demodomain.model.MealItemDetails
import demo.demodomain.repository.GetMealDetailsRepository

class GetMealDetailImpl(private val findYourMealAPi: FindYourMealAPi) : GetMealDetailsRepository {
    override suspend fun getMealDetails(id: String): MealItemDetails {
      return findYourMealAPi.getMealDetails(id).meals[0].toDomainMealItemDetails()
    }
}