package com.ghg.favmusicapp.data.di

import com.ghg.favmusicapp.data.api.ItunesApi
import com.ghg.favmusicapp.data.repository.ItunesRepositoryImpl
import com.ghg.favmusicapp.domain.repository.ItunesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

  @Provides
  @ActivityRetainedScoped
  fun provideItunesRepository(
    api: ItunesApi
  ): ItunesRepository = ItunesRepositoryImpl(api)
}