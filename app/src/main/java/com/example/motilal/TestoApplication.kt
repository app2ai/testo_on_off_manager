package com.example.motilal

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.motilal.bg_task.RepoWorker
import com.example.motilal.db.RepoRoomDB
import com.example.motilal.repository.ReposRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

class TestoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        syncDatabase()
        startWorkRequest()
    }

    val database by lazy { RepoRoomDB.getDb(this) }
    val repository by lazy { ReposRepository(database.repoDao()) }

    private fun syncDatabase() {
        println("Data from API: called")
        runBlocking {
            launch(Dispatchers.IO) {
                repository.fetchAllRepoFromApi()
            }
        }
    }

    private fun startWorkRequest() {
        Log.d("TAG", "periodicRequest: Begin")
        var myConstraints: Constraints? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            myConstraints = Constraints.Builder()
                .setRequiresDeviceIdle(false)
                .setRequiresCharging(false)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(true)
                .setRequiresStorageNotLow(true)
                .build()
            val workRequest = PeriodicWorkRequestBuilder<RepoWorker>(15, TimeUnit.MINUTES)
                .setConstraints(myConstraints)
                .addTag("repo")
                .build()
            WorkManager.getInstance(this).enqueue(workRequest)
        } else {
            val workRequest = PeriodicWorkRequestBuilder<RepoWorker>(15, TimeUnit.MINUTES)
                .addTag("repo")
                .build()
            WorkManager.getInstance(this).enqueue(workRequest)
        }
    }
}