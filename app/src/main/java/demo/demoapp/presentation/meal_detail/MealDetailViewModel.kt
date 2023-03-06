package demo.demoapp.presentation.meal_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import demo.democommon.Resource
import demo.demodomain.use_case.GetMealDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MealDetailViewModel @Inject constructor(private val getMealDetailUseCase: GetMealDetailUseCase): ViewModel(){

    private val _mealDetails = MutableStateFlow<MealDetailState>(MealDetailState())
    val mealDetails : StateFlow<MealDetailState> = _mealDetails

    fun getMealDetails(id: String){
        getMealDetailUseCase(id).onEach {
            when (it){
                is Resource.Loading ->{
                   _mealDetails.value = MealDetailState(isLoading = true)
                }
                is Resource.Error -> {
                    _mealDetails.value = MealDetailState(error = it.message?:"")
                }
                is Resource.Success -> {
                    _mealDetails.value = MealDetailState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }



}