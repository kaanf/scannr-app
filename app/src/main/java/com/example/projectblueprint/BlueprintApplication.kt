package com.example.projectblueprint

import android.content.Context
import com.example.projectblueprint.framework.application.CoreApplication
import com.example.projectblueprint.framework.provider.AppInitializer
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class BlueprintApplication: CoreApplication() {
    companion object {
        private var application: BlueprintApplication? = null

        fun getContext(): Context? {
            return application?.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        application = this@BlueprintApplication
    }
}