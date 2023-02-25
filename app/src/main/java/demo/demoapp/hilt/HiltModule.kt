package demo.demoapp.hilt
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import demo.democommon.Constants

import demo.demodata.remote.FindYourMealAPi
import demo.demodata.repository.GetMealDetailImpl
import demo.demodata.repository.GetMealListImpl
import demo.demodomain.repository.FindYourMealRepository
import demo.demodomain.repository.GetMealDetailsRepository

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {
    @Provides
    @Singleton
    fun provideFindYourMealAPi(): FindYourMealAPi {
        return Retrofit.Builder().baseUrl(Constants.Base_Url).
        addConverterFactory(GsonConverterFactory.create()).build()
            .create(FindYourMealAPi::class.java)
    }

    // for getting MealRepository
    @Provides
    fun provideFindYourMealRepository(findYourMealAPi: FindYourMealAPi): FindYourMealRepository {
        return GetMealListImpl(findYourMealAPi)
    }

   // for getting MealDetailRepository
   @Provides
   fun provideMealDetailsRepository(findYourMealAPi: FindYourMealAPi): GetMealDetailsRepository {
       return GetMealDetailImpl(findYourMealAPi)
   }

}