package com.baec23.tenaday.di

import android.content.Context
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.DialogNavigator
import com.baec23.tenaday.api.TenADayApi
import com.baec23.tenaday.navigation.NavService
import com.baec23.tenaday.repository.QuizQuestionRepository
import com.baec23.tenaday.repository.SampleItemRepository
import com.baec23.tenaday.repository.WordRepository
import com.baec23.tenaday.service.SnackbarService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideNavController(@ApplicationContext context: Context) =
        NavHostController(context).apply {
            navigatorProvider.addNavigator(ComposeNavigator())
            navigatorProvider.addNavigator(DialogNavigator())
        }

    @Singleton
    @Provides
    fun provideNavigationService(navController: NavHostController) =
        NavService(navController = navController)

    @Singleton
    @Provides
    fun provideSnackbarService() = SnackbarService()

    @Singleton
    @Provides
    fun provideSampleItemRepository() = SampleItemRepository()

    @Singleton
    @Provides
    fun provideWordRepository(tenADayApi: TenADayApi) = WordRepository(tenADayApi)

    @Singleton
    @Provides
    fun provideQuizQuestionRepository(tenADayApi: TenADayApi) = QuizQuestionRepository(tenADayApi)

    @Singleton
    @Provides
    fun provideTenADayApi(): TenADayApi = Retrofit.Builder().baseUrl(TenADayApi.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create()).build().create()
}
