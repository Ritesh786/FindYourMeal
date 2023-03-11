package demo.demodata.di
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import demo.democommon.Constants
import demo.demodata.remote.FindYourMealAPi
import demo.demodata.repository.FindYourMealRepositoryImpl
import demo.demodomain.repository.FindYourMealRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataModule {

    @Provides
    @Singleton
    fun provideFindYourMealAPi(): FindYourMealAPi {
        return Retrofit.Builder().baseUrl(Constants.Base_Url).
        addConverterFactory(GsonConverterFactory.create()).build()
            .create(FindYourMealAPi::class.java)
    }

    @Provides
    fun provideFindYourMealRepository(findYourMealAPi: FindYourMealAPi): FindYourMealRepository {
        return FindYourMealRepositoryImpl(findYourMealAPi)
    }
}