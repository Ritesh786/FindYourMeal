package demo.demodomain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import demo.demodomain.use_case.GetMealDetailUseCase
import demo.demodomain.use_case.GetMealDetailUseCaseImpl
import demo.demodomain.use_case.GetMealItemListUseCase
import demo.demodomain.use_case.GetMealItemListUseCaseImpl
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class DomainModule {
    @Binds
    @Singleton
    internal abstract fun bindGetMealDetailsUseCase(getMealDetailUseCaseImpl: GetMealDetailUseCaseImpl): GetMealDetailUseCase

    @Binds
    @Singleton
    internal abstract fun bindGetMealItemListUseCase(getMealItemListUseCaseImpl: GetMealItemListUseCaseImpl): GetMealItemListUseCase
}