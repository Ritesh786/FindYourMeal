package transport.riteshdom.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import transport.ritesh.demoapp.common.Resource
import transport.ritesh.demoapp.data.model.toDomainMeal
import transport.riteshdom.model.Meal
import transport.riteshdom.repository.FindYourMealRepository
import java.io.IOException
import javax.inject.Inject

class GetMealItemListUseCase
@Inject constructor(private val repository: FindYourMealRepository)  {
    operator fun invoke(s:String):Flow<Resource<List<Meal>>> = flow {
       try {
          emit(Resource.Loading())
          val response = repository.getMealList(s)
          val mealList = if(response.meals.isNullOrEmpty()) emptyList<Meal>() else response. { it.toDomainMeal() }
          emit(Resource.Success(data = mealList))
       }catch (e:HttpException){
           emit(Resource.Error(message = e.localizedMessage?:"unknown error"))
       }catch (e:IOException){
           emit(Resource.Error(message = e.localizedMessage?:"Check Your Internet Connection"))
       }catch (e:Exception){
           emit(Resource.Error(message = e.localizedMessage?:""))
       }
    }
}