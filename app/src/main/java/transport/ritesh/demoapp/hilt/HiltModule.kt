package transport.ritesh.demoapp.hilt
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import transport.ritesh.demoapp.common.Constants
import transport.ritesh.demoapp.data.remote.FindYourMealAPi
import transport.ritesh.demoapp.data.repository.GetMealDetailImpl
import transport.ritesh.demoapp.data.repository.GetMealListImpl
import transport.ritesh.demoapp.domain.repository.FindYourMealRepository
import transport.ritesh.demoapp.domain.repository.GetMealDetailsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {
    @Provides
    @Singleton
    fun provideFindYourMealAPi():FindYourMealAPi{
        return Retrofit.Builder().baseUrl(Constants.Base_Url).
        addConverterFactory(GsonConverterFactory.create()).build()
            .create(FindYourMealAPi::class.java)
    }

    // for getting MealRepository
    @Provides
    fun provideFindYourMealRepository(findYourMealAPi: FindYourMealAPi):FindYourMealRepository{
        return GetMealListImpl(findYourMealAPi)
    }

   // for getting MealDetailRepository
   @Provides
   fun provideMealDetailsRepository(findYourMealAPi: FindYourMealAPi):GetMealDetailsRepository{
       return GetMealDetailImpl(findYourMealAPi)
   }

}