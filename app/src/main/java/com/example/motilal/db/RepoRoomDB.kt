package com.example.motilal.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.motilal.models.RepositoriesItem

@Database(entities = arrayOf(RepositoriesItem::class), version = 1, exportSchema = false)
abstract class RepoRoomDB: RoomDatabase() {
    abstract fun repoDao(): RepoDao

    companion object{
        @Volatile
        private var INSTANCE: RepoRoomDB? = null

        fun getDb(context: Context): RepoRoomDB{
            return INSTANCE?: synchronized(this){
                val insta = Room.databaseBuilder(
                    context.applicationContext,
                    RepoRoomDB::class.java,
                    "repo_db"
                ).build()
                INSTANCE = insta
                insta
            }
        }
    }
}
