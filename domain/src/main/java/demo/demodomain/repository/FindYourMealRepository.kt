package demo.demodomain.repository

import demo.demodomain.model.Meal
import demo.demodomain.model.MealItemDetails

interface FindYourMealRepository {

    suspend fun getMealList(s:String): List<Meal>

    suspend fun getMealDetails(id: String): MealItemDetails
}