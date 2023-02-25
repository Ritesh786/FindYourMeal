package transport.riteshdom.repository

import transport.riteshdata.model.MealItemsDTO
import transport.riteshdom.model.Meal


interface FindYourMealRepository {
    suspend fun getMealList(s:String): MealItemsDTO
}