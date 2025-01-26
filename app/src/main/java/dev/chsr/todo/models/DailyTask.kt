package dev.chsr.todo.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalTime
import java.util.Date

@Entity(tableName = "daily_tasks")
data class DailyTask(
    @PrimaryKey(autoGenerate = true)
    override val id: Int = 0,
    override val task: String,
    val status: TaskStatus,
    @ColumnInfo("completed_at")
    val completedAt: Date,
    @ColumnInfo("reset_time")
    val resetTime: LocalTime,
    @ColumnInfo("completion_streak")
    val completionStreak: Int
) : Task
