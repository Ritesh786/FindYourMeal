package demo.demodata.repository

import demo.demodata.mapper.toDomainMeal
import demo.demodata.mapper.toDomainMealItemDetails
import demo.demodata.remote.FindYourMealAPi
import demo.demodomain.model.Meal
import demo.demodomain.model.MealItemDetails
import demo.demodomain.repository.FindYourMealRepository

class FindYourMealRepositoryImpl(private val findYourMealAPi: FindYourMealAPi) : FindYourMealRepository {

    override suspend fun getMealList(s: String): List<Meal> {
        return findYourMealAPi.getMealItemList(s).meals.map{ it.toDomainMeal() }
    }

    override suspend fun getMealDetails(id: String): MealItemDetails {
        return findYourMealAPi.getMealDetails(id).meals.first().toDomainMealItemDetails()
    }
}