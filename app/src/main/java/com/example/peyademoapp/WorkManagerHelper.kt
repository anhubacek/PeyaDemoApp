package com.example.peyademoapp

import android.content.Context
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkManagerHelper
@Inject constructor(
    @ApplicationContext private val context: Context,
) {

    val workManager = WorkManager.getInstance(context)

    inline fun <reified T : CoroutineWorker> schedulePeriodicTask(
        uniqueWorkName: String,
        repeatInterval: Long = 15,
        timeUnit: TimeUnit = TimeUnit.MINUTES,
        networkRequired: Boolean = true,
    ) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(
                if (networkRequired) NetworkType.CONNECTED
                else NetworkType.NOT_REQUIRED
            )
            .build()

        val periodicWorkRequest = PeriodicWorkRequestBuilder<T>(
            repeatInterval,
            timeUnit
        )
            .setConstraints(constraints)
            .build()

        workManager.enqueueUniquePeriodicWork(
            uniqueWorkName,
            ExistingPeriodicWorkPolicy.KEEP,
            periodicWorkRequest
        )
    }

    inline fun <reified T : CoroutineWorker> enqueueOneTimeTask(
        uniqueWorkName: String,
        networkRequired: Boolean = true,
    ) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(
                if (networkRequired) NetworkType.CONNECTED
                else NetworkType.NOT_REQUIRED
            )
            .build()

        val oneTimeWorkRequest = OneTimeWorkRequestBuilder<T>()
            .setConstraints(constraints)
            .build()

        if (uniqueWorkName != null) {
            workManager.enqueueUniqueWork(
                uniqueWorkName,
                ExistingWorkPolicy.REPLACE,
                oneTimeWorkRequest
            )
        } else {
            workManager.enqueue(oneTimeWorkRequest)
        }
    }

    fun cancelWork(uniqueWorkName: String) {
        workManager.cancelUniqueWork(uniqueWorkName)
    }

}