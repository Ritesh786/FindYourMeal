package transport.ritesh.demoapp.domain.repository

import transport.ritesh.demoapp.data.model.MealItemsDTO

interface FindYourMealRepository {
    suspend fun getMealList(s:String):MealItemsDTO
}