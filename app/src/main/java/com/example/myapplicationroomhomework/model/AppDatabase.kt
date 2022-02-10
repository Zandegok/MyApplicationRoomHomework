package com.example.myapplicationroomhomework.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplicationroomhomework.model.entities.KeyValuePair
import com.example.myapplicationroomhomework.model.tables.KeyValuePairsRepository

@Database(entities = [KeyValuePair::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val keyValuePairsRepository: KeyValuePairsRepository

    companion object {

        @Volatile
        private var _instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = _instance

                if (instance == null) {

                    instance = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "app_database"
                    ).fallbackToDestructiveMigration().build()

                    _instance = instance
                }

                return instance
            }
        }
    }

}