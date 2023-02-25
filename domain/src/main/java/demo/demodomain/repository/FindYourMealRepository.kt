package demo.demodomain.repository

import demo.demodomain.model.Meal


interface FindYourMealRepository {
    suspend fun getMealList(s:String): List<Meal>
}