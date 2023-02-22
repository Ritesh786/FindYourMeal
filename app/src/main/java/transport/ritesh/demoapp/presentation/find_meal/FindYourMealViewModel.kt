package transport.ritesh.demoapp.presentation.find_meal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import transport.ritesh.demoapp.common.Resource
import transport.ritesh.demoapp.domain.use_case.GetMealItemListUseCase
import javax.inject.Inject

@HiltViewModel
class FindYourMealViewModel
@Inject constructor(private val getMealItemListUseCase: GetMealItemListUseCase) : ViewModel() {
    private val _findYourMealList = MutableStateFlow<FindYourMealState>(FindYourMealState())
    val findYourMealList : StateFlow<FindYourMealState> = _findYourMealList
    fun findYourMealList(s:String){
        getMealItemListUseCase(s).onEach {
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