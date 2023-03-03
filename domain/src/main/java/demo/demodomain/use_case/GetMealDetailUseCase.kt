package demo.demodomain.use_case

import demo.democommon.Resource
import demo.demodomain.model.MealItemDetails
import kotlinx.coroutines.flow.Flow

interface GetMealDetailUseCase {
    operator fun invoke(id:String): Flow<Resource<MealItemDetails>>
}