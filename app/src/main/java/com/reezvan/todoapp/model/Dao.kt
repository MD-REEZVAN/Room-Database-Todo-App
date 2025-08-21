package com.reezvan.todoapp.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkDao {
    @Delete
    suspend fun deleteWork(workEntity: WorkModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWork(workEntity: WorkModel)

    @Query("Select * from work_table" )
    fun getAllWork(): Flow<List<WorkModel>>

    @Query("Select * from work_table where id =:id")
    fun getWorkById(id: Long): Flow<WorkModel>

    @Update
    suspend fun updateWork(workEntity: WorkModel)

}