package transport.ritesh.demoapp.domain.repository

import transport.ritesh.demoapp.data.model.MealItemsDTO

interface GetMealDetailsRepository {
    suspend fun getMealDetails(id: String):MealItemsDTO
}