package com.example.motilal.bg_task

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.motilal.TestoApplication
import com.kldep.app.api.ApiInterface
import com.kldep.app.api.ClientHttpRetro
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RepoWorker(context: Context, workParams: WorkerParameters): Worker(context, workParams) {
    private val retrofit by lazy {
        ClientHttpRetro.getRetrofit()
    }
    override fun doWork(): Result {
        runBlocking {
            launch(Dispatchers.IO) {
                val d = retrofit.create(ApiInterface::class.java).getAllRepoApi().execute()
                if (d.isSuccessful){
                    val dao = (applicationContext as TestoApplication).database.repoDao()
                    dao.deleteTableItems()
                    val l = dao.insertRepo(d.body()!!.toList())
                    Log.d("TAG", "doWork: inserted data: ${l.size}")
                } else {
                    Result.failure()
                }
            }
        }
        return Result.success()
    }
}