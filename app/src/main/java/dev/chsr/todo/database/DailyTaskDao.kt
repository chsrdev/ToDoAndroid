package dev.chsr.todo.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import dev.chsr.todo.models.DailyTask

@Dao
interface DailyTaskDao {
    @Query("SELECT * FROM daily_tasks")
    suspend fun getTasks(): List<DailyTask>

    @Insert
    suspend fun insert(task: DailyTask)

    @Delete
    suspend fun delete(task: DailyTask)

    @Update
    suspend fun updateTask(task: DailyTask)
}