package demo.demoapp.presentation.find_meal

import demo.demodomain.model.Meal

data class FindYourMealState(
    val data: List<Meal>? = null,
    val error: String = "",
    val isLoading:Boolean = false
)
