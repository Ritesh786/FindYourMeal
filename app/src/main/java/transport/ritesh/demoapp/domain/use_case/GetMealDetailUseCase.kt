package transport.ritesh.demoapp.domain.use_case
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import transport.ritesh.demoapp.common.Resource
import transport.ritesh.demoapp.data.model.toDomainMealItemDetails
import transport.ritesh.demoapp.domain.model.MealItemDetails
import transport.ritesh.demoapp.domain.repository.GetMealDetailsRepository
import java.io.IOException
import javax.inject.Inject
class GetMealDetailUseCase @Inject constructor(private val getMealDetailsRepository: GetMealDetailsRepository) {
    operator fun invoke(id:String):Flow<Resource<MealItemDetails>> = flow {
        try {
            emit(Resource.Loading())
            val response = getMealDetailsRepository.getMealDetails(id).meals[0].toDomainMealItemDetails()
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