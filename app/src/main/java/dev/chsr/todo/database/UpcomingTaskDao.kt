package dev.chsr.todo.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import dev.chsr.todo.models.UpcomingTask

@Dao
interface UpcomingTaskDao {
    @Query("SELECT * FROM upcoming_tasks")
    suspend fun getTasks(): List<UpcomingTask>

    @Insert
    suspend fun insert(task: UpcomingTask)

    @Delete
    suspend fun delete(task: UpcomingTask)

    @Update
    suspend fun updateTask(task: UpcomingTask)
}