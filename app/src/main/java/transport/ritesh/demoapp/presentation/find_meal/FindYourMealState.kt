package transport.ritesh.demoapp.presentation.find_meal

import transport.ritesh.demoapp.domain.model.Meal

data class FindYourMealState(
    val data: List<Meal>? = null,
    val error: String = "",
    val isLoading:Boolean = false
)
