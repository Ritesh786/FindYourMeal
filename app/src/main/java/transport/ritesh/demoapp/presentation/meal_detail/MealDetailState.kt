package transport.ritesh.demoapp.presentation.meal_detail

import transport.ritesh.demoapp.domain.model.MealItemDetails

data class MealDetailState(
    val data:MealItemDetails? = null,
    val error:String? = "",
    val isLoading: Boolean? = false
)
