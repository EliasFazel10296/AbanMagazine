/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 11/20/20 9:00 AM
 * Last modified 11/20/20 8:52 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine

import android.app.Application
import com.abanabsalan.aban.magazine.PostsConfigurations.OfflineDatabase.Firestore.FirestoreConfiguration
import com.abanabsalan.aban.magazine.Utils.DependencyInjections.DaggerDependencyGraph
import com.abanabsalan.aban.magazine.Utils.DependencyInjections.DependencyGraph
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.firestore.FirebaseFirestore

class AbanMagazinePhoneApplication : Application() {

    val firestoreConfiguration: FirestoreConfiguration by lazy {
        FirestoreConfiguration(applicationContext)
    }

    lateinit var firestoreDatabase: FirebaseFirestore

    val dependencyGraph: DependencyGraph by lazy {
        DaggerDependencyGraph.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()

        val firebaseAnalytics = FirebaseAnalytics.getInstance(applicationContext)

        firebaseAnalytics.setAnalyticsCollectionEnabled(!BuildConfig.DEBUG)

        firestoreDatabase = FirestoreConfiguration(applicationContext).initialize()

    }
}