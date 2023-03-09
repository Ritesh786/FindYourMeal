package demo.demodomain.use_case

import demo.democommon.Resource
import demo.demodomain.model.Meal
import kotlinx.coroutines.flow.Flow

interface GetMealItemListUseCase {

    operator fun invoke(s:String): Flow<Resource<List<Meal>>>
}