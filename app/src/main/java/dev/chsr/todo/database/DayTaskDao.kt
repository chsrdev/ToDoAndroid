package dev.chsr.todo.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import dev.chsr.todo.models.DayTask

@Dao
interface DayTaskDao {
    @Query("SELECT * FROM day_tasks")
    suspend fun getTasks(): List<DayTask>

    @Insert
    suspend fun insert(task: DayTask)

    @Delete
    suspend fun delete(task: DayTask)

    @Update
    suspend fun updateTask(task: DayTask)
}