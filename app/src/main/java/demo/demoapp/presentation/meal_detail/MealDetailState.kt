package demo.demoapp.presentation.meal_detail

import demo.demodomain.model.MealItemDetails


data class MealDetailState(
    val data: MealItemDetails? = null,
    val error:String? = "",
    val isLoading: Boolean? = false
)
