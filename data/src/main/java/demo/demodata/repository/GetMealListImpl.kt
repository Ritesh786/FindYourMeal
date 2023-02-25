package demo.demodata.repository

import demo.demodata.model.toDomainMeal
import demo.demodata.remote.FindYourMealAPi
import demo.demodomain.model.Meal
import demo.demodomain.repository.FindYourMealRepository


class GetMealListImpl(private val findYourMealAPi: FindYourMealAPi) : FindYourMealRepository {
    override suspend fun getMealList(s: String): List<Meal> {
        return findYourMealAPi.getMealItemList(s).meals.map{ it.toDomainMeal() }
    }
}