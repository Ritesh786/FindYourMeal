package demo.demodata.mapper

import demo.demodata.model.MealDTO
import demo.demodomain.model.Meal
import demo.demodomain.model.MealItemDetails

fun MealDTO.toDomainMeal(): Meal {
    return Meal(
        mealId = this.idMeal ?: "",
        name = this.strMeal ?: "",
        image = this.strMealThumb ?: ""
    )
}

fun MealDTO.toDomainMealItemDetails(): MealItemDetails {
    return MealItemDetails(
        name = this.strMeal ?: "",
        image = this.strMealThumb ?: "",
        instructions = this.strInstructions ?: "",
        category = this.strCategory ?: "",


        ingredient1 = this.strIngredient1 ?: "",
        ingredient2 = this.strIngredient2 ?: "",
        ingredient3 = this.strIngredient3 ?: "",
        ingredient4 = this.strIngredient4 ?: "",
        ingredient5 = this.strIngredient5 ?: "",
        ingredient6 = this.strIngredient6 ?: "",
        ingredient7 = this.strIngredient7 ?: "",
        ingredient8 = this.strIngredient8 ?: "",
        ingredient9 = this.strIngredient9 ?: "",
        ingredient10 = this.strIngredient10 ?: "",
        ingredient11 = this.strIngredient11 ?: "",
        ingredient12 = this.strIngredient12 ?: "",
        ingredient13 = this.strIngredient13 ?: "",
        ingredient14 = this.strIngredient14 ?: "",
        ingredient15 = this.strIngredient15 ?: "",
        ingredient16 = this.strIngredient16 ?: "",
        ingredient17 = this.strIngredient17 ?: "",
        ingredient18 = this.strIngredient18 ?: "",
        ingredient19 = this.strIngredient19 ?: "",
        ingredient20 = this.strIngredient20 ?: "",

        measure1 = this.strMeasure1 ?: "",
        measure2 = this.strMeasure2 ?: "",
        measure3 = this.strMeasure3 ?: "",
        measure4 = this.strMeasure4 ?: "",
        measure5 = this.strMeasure5 ?: "",
        measure6 = this.strMeasure6 ?: "",
        measure7 = this.strMeasure7 ?: "",
        measure8 = this.strMeasure8 ?: "",
        measure9 = this.strMeasure9 ?: "",
        measure10 = this.strMeasure10 ?: "",
        measure11 = this.strMeasure11 ?: "",
        measure12 = this.strMeasure12 ?: "",
        measure13 = this.strMeasure13 ?: "",
        measure14 = this.strMeasure14 ?: "",
        measure15 = this.strMeasure15 ?: "",
        measure16 = this.strMeasure16 ?: "",
        measure17 = this.strMeasure17 ?: "",
        measure18 = this.strMeasure18 ?: "",
        measure19 = this.strMeasure19 ?: "",
        measure20 = this.strMeasure20 ?: "",
    )
}