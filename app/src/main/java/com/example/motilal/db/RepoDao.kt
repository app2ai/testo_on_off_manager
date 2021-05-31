package com.example.motilal.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.motilal.models.RepositoriesItem

@Dao
interface RepoDao {
    @Query("SELECT * FROM repo_items")
    fun getRepoItems(): List<RepositoriesItem>

    @Insert
    fun insertRepo(lst:List<RepositoriesItem>): List<Long>

    @Query("DELETE FROM repo_items")
    fun deleteTableItems()
}
