package com.example.myapplicationroomhomework.model.tables

import androidx.room.*
import com.example.myapplicationroomhomework.model.entities.Record


@Dao
interface RecordDao {
    @Query("SELECT * from records")
    suspend fun getAll():List<Record>

    @Query("SELECT * from records WHERE id = :id")
    suspend fun getById(id: Int): Record?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(record: Record):Long

    @Update
    suspend fun update(record: Record)

    @Delete
    suspend fun delete(record: Record)

    @Delete(entity = Record::class)
    suspend fun deleteAll(records:List<Record>)
    @Query( "DELETE FROM records")
    suspend fun deleteAll()
}