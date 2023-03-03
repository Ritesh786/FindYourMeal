package demo.demodomain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import demo.demodomain.repository.FindYourMealRepository
import demo.demodomain.use_case.GetMealDetailUseCase
import demo.demodomain.use_case.GetMealDetailUseCaseImpl
import demo.demodomain.use_case.GetMealItemListUseCase
import demo.demodomain.use_case.GetMealItemListUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    @Singleton
    fun bindGetMealDetailsUseCase(findYourMealRepository: FindYourMealRepository): GetMealDetailUseCase{
        return GetMealDetailUseCaseImpl(findYourMealRepository)
    }

    @Provides
    @Singleton
    fun bindGetMealItemListUseCase(findYourMealRepository: FindYourMealRepository): GetMealItemListUseCase{
        return GetMealItemListUseCaseImpl(findYourMealRepository)
    }
}