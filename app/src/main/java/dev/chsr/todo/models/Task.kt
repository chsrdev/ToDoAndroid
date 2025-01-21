package dev.chsr.todo.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalTime
import java.util.Date

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val task: String,
    val category: TaskCategory,
    val status: TaskStatus,
    @ColumnInfo("completed_at")
    val completedAt: Date,
    @ColumnInfo("reset_time")
    val resetTime: LocalTime,
    @ColumnInfo("completion_streak")
    val completionStreak: Int
)