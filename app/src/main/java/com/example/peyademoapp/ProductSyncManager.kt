package com.example.peyademoapp

import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ProductSyncManager
@Inject constructor(
    private val workManagerHelper: WorkManagerHelper
) {

    fun schedulePeriodicSync() {
        workManagerHelper.schedulePeriodicTask<ProductSyncWorker>(
            uniqueWorkName = "PRODUCT_SYNC_WORK",
            repeatInterval = 15,
            timeUnit = TimeUnit.MINUTES
        )
    }

    fun syncNow() {
        workManagerHelper.enqueueOneTimeTask<ProductSyncWorker>(
            uniqueWorkName = "PRODUCT_SYNC_WORK_NOW"
        )
    }
}