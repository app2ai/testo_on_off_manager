package com.example.motilal.repository

import android.util.Log
import com.example.motilal.db.RepoDao
import com.example.motilal.models.RepositoriesItem
import com.kldep.app.api.ApiInterface
import com.kldep.app.api.ClientHttpRetro

class ReposRepository(private val repoDao: RepoDao) {
    private val retrofit by lazy {
        ClientHttpRetro.getRetrofit()
    }

    fun getAllRepos(): List<RepositoriesItem>{
        return repoDao.getRepoItems()
    }

    fun fetchAllRepoFromApi(){
         try {
            val data = retrofit.create(ApiInterface::class.java).getAllRepoApi().execute()
            Log.d("TAG", "getAllRepo: $data and ${data.code()}")

            when{
                data.isSuccessful->{
                    // Store data into database
                    repoDao.deleteTableItems()
                    val l = repoDao.insertRepo(data.body()!!.toList())
                }
                else->{
                    Log.d("TAG", "fetchAllRepoFromApi: Failed to fetch data")
                }
            }
        } catch (ex: Exception){
            Log.d("TAG", "Exception: ${ex.message}")
        }
    }
}