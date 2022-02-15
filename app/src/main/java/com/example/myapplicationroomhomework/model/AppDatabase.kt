package com.example.myapplicationroomhomework.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplicationroomhomework.model.entities.Record
import com.example.myapplicationroomhomework.model.tables.RecordDao

@Database(entities = [Record::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val recordDao: RecordDao

    companion object {

        @Volatile
        private var _instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                val instance = _instance
                return instance?:Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database"
                ).fallbackToDestructiveMigration().build().also { _instance = it }
            }
        }
    }
}