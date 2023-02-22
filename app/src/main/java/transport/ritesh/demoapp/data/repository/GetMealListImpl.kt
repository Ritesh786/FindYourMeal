package transport.ritesh.demoapp.data.repository

import transport.ritesh.demoapp.data.model.MealItemsDTO
import transport.ritesh.demoapp.data.remote.FindYourMealAPi
import transport.ritesh.demoapp.domain.repository.FindYourMealRepository

class GetMealListImpl(private val findYourMealAPi: FindYourMealAPi) : FindYourMealRepository {
    override suspend fun getMealList(s: String): MealItemsDTO {
        return findYourMealAPi.getMealItemList(s)
    }
}