package demo.demodomain.use_case

import demo.democommon.Resource
import demo.demodomain.model.Meal
import kotlinx.coroutines.flow.Flow

interface GetMealItemListUseCase {

    operator fun invoke(name:String): Flow<Resource<List<Meal>>>
}