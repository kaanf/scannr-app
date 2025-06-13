package com.example.projectblueprint.di

import com.example.projectblueprint.BlueprintApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Provides
    @Singleton
    fun provideApplication(): BlueprintApplication = BlueprintApplication()
}