package demo.demodomain.use_case
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import demo.democommon.Resource
import demo.democommon.getException
import demo.demodomain.model.MealItemDetails
import demo.demodomain.repository.FindYourMealRepository


import javax.inject.Inject

class GetMealDetailUseCaseImpl @Inject constructor(private val findYourMealRepository: FindYourMealRepository) : GetMealDetailUseCase{

    override operator fun invoke(id:String):Flow<Resource<MealItemDetails>> = flow {
        try {
            emit(Resource.Loading())
            val response = findYourMealRepository.getMealDetails(id)
            emit(Resource.Success(data = response))
        }catch (e: Exception){
            emit(Resource.Error(message = getException(e)))
        }
    }
}