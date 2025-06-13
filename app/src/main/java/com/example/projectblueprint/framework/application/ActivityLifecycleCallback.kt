package com.example.projectblueprint.framework.application

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import timber.log.Timber

class ActivityLifecycleCallback : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            Timber.tag(activity.packageName).d("onCreate()")
            /* activity.allowDebugRotation() */
            activity.registerFragmentLifecycleCallbacks()
        }

        override fun onActivityStarted(activity: Activity) {
            Timber.tag(activity.packageName).d("onStart()")
        }

        override fun onActivityResumed(activity: Activity) {
            Timber.tag(activity.packageName).d("onResume()")
        }

        override fun onActivityPaused(activity: Activity) {
            Timber.tag(activity.packageName).d("onPause()")
        }

        override fun onActivityStopped(activity: Activity) {
            Timber.tag(activity.packageName).d("onStop()")
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            Timber.tag(activity.packageName).d("onSaveInstanceState()")
        }

        override fun onActivityDestroyed(activity: Activity) {
            Timber.tag(activity.packageName).d("onDestroy()")
        }
    }

    /*
    private fun Activity.allowDebugRotation() {
        requestedOrientation = if (BuildConfig.DEBUG) {
            ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        } else {
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }
     */

    fun Activity.registerFragmentLifecycleCallbacks() {
        if (this is FragmentActivity) {
            supportFragmentManager
                .registerFragmentLifecycleCallbacks(
                    object : FragmentManager.FragmentLifecycleCallbacks() {
                        override fun onFragmentViewCreated(
                            fm: FragmentManager,
                            f: Fragment,
                            v: View,
                            savedInstanceState: Bundle?
                        ) {
                            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
                            Timber.tag(f.id.toString()).d("onCreateView()")
                        }

                        override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
                            super.onFragmentResumed(fm, f)
                            Timber.tag(f.id.toString()).d("onResume()")
                        }

                        override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
                            super.onFragmentPaused(fm, f)
                            Timber.tag(f.id.toString()).d("onPause()")
                        }

                        override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
                            super.onFragmentViewDestroyed(fm, f)
                            Timber.tag(f.id.toString()).d("onDestroyView()")
                        }
                    },
                    true
                )
        }
}
