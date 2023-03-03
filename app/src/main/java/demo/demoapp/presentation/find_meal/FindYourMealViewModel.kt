package demo.demoapp.presentation.find_meal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import demo.democommon.Resource
import demo.demodomain.use_case.GetMealItemListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FindYourMealViewModel
@Inject constructor(private val getMealItemListUse: GetMealItemListUseCase) : ViewModel() {
    private val _findYourMealList = MutableStateFlow(FindYourMealState())
    val findYourMealList : StateFlow<FindYourMealState> = _findYourMealList
    fun findYourMealList(s:String){
        getMealItemListUse(s).onEach {
            when (it){
               is Resource.Loading -> {
                   _findYourMealList.value = FindYourMealState(isLoading = true)
               }
                is Resource.Error -> {
                   _findYourMealList.value = FindYourMealState(error = it.message?:"")
                }
                is Resource.Success -> {
                    _findYourMealList.value = FindYourMealState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }

}