package demo.demodomain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import demo.democommon.Resource
import demo.democommon.getException
import demo.demodomain.model.Meal
import demo.demodomain.repository.FindYourMealRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMealItemListUseCaseImpl
@Inject constructor(private val findYourMealRepository: FindYourMealRepository)  : GetMealItemListUseCase{

   override operator fun invoke(s:String):Flow<Resource<List<Meal>>> = flow {
       try {
          emit(Resource.Loading())
          val response = findYourMealRepository.getMealList(s)
          val mealList = response.ifEmpty { emptyList() }
          emit(Resource.Success(data = mealList))
       }catch (e: Exception){
           emit(Resource.Error(message = getException(e)))
       }
    }
}