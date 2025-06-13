package com.example.projectblueprint.framework.provider

import com.example.projectblueprint.framework.application.CoreApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

interface AppInitializer {
    fun initialize(application: CoreApplication)
}