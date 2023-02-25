package transport.riteshdom.use_case
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import transport.riteshdata.model.toDomainMealItemDetails
import transport.riteshdom.model.MealItemDetails
import transport.riteshdom.repository.GetMealDetailsRepository
import java.io.IOException
import javax.inject.Inject
class GetMealDetailUseCase @Inject constructor(private val getMealDetailsRepository: GetMealDetailsRepository) {
    operator fun invoke(id:String):Flow<Resource<MealItemDetails>> = flow {
        try {
            emit(Resource.Loading())
            val response = getMealDetailsRepository.getMealDetails(id).
            emit(Resource.Success(data = response))
        }catch (e: HttpException){
            emit(Resource.Error(message = e.localizedMessage?:"unknown error"))
        }catch (e: IOException){
            emit(Resource.Error(message = e.localizedMessage?:"Check Your Internet Connection"))
        }catch (e:Exception){
            emit(Resource.Error(message = e.localizedMessage?:""))
        }
    }
}