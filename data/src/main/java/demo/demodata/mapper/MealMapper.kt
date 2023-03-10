package demo.demodata.mapper

import demo.demodata.model.MealDTO
import demo.demodomain.model.Meal
import demo.demodomain.model.MealItemDetails

fun MealDTO.toDomainMeal(): Meal {
    return Meal(
        mealId = idMeal ?: "",
        name =  strMeal ?: "",
        image = strMealThumb ?: ""
    )
}

fun MealDTO.toDomainMealItemDetails(): MealItemDetails {
    return MealItemDetails(
        name = strMeal ?: "",
        image = strMealThumb ?: "",
        instructions = strInstructions ?: "",
        category = strCategory ?: "",
        ingredient1 = strIngredient1 ?: "",
        ingredient2 = strIngredient2 ?: "",
        ingredient3 = strIngredient3 ?: "",
        ingredient4 = strIngredient4 ?: "",
        ingredient5 = strIngredient5 ?: "",
        ingredient6 = strIngredient6 ?: "",
        ingredient7 = strIngredient7 ?: "",
        ingredient8 = strIngredient8 ?: "",
        ingredient9 = strIngredient9 ?: "",
        ingredient10 = strIngredient10 ?: "",
        ingredient11 = strIngredient11 ?: "",
        ingredient12 = strIngredient12 ?: "",
        ingredient13 = strIngredient13 ?: "",
        ingredient14 = strIngredient14 ?: "",
        ingredient15 = strIngredient15 ?: "",
        ingredient16 = strIngredient16 ?: "",
        ingredient17 = strIngredient17 ?: "",
        ingredient18 = strIngredient18 ?: "",
        ingredient19 = strIngredient19 ?: "",
        ingredient20 = strIngredient20 ?: "",
        measure1 = strMeasure1 ?: "",
        measure2 = strMeasure2 ?: "",
        measure3 = strMeasure3 ?: "",
        measure4 = strMeasure4 ?: "",
        measure5 = strMeasure5 ?: "",
        measure6 = strMeasure6 ?: "",
        measure7 = strMeasure7 ?: "",
        measure8 = strMeasure8 ?: "",
        measure9 = strMeasure9 ?: "",
        measure10 = strMeasure10 ?: "",
        measure11 = strMeasure11 ?: "",
        measure12 = strMeasure12 ?: "",
        measure13 = strMeasure13 ?: "",
        measure14 = strMeasure14 ?: "",
        measure15 = strMeasure15 ?: "",
        measure16 = strMeasure16 ?: "",
        measure17 = strMeasure17 ?: "",
        measure18 = strMeasure18 ?: "",
        measure19 = strMeasure19 ?: "",
        measure20 = strMeasure20 ?: "",
    )
}