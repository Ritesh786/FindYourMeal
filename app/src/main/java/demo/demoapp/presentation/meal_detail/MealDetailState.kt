package demo.demoapp.presentation.meal_detail

import demo.demodomain.model.MealItemDetails

data class MealDetailState(
    var data: MealItemDetails? = null,
    val error:String? = "",
    val isLoading: Boolean? = false
)
