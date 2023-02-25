package transport.riteshdata.repository

import transport.riteshdata.model.MealItemsDTO
import transport.riteshdata.remote.FindYourMealAPi
import transport.riteshdom.repository.FindYourMealRepository


class GetMealListImpl(private val findYourMealAPi: FindYourMealAPi) : FindYourMealRepository {
    override suspend fun getMealList(s: String): MealItemsDTO {
        return findYourMealAPi.getMealItemList(s)
    }
}