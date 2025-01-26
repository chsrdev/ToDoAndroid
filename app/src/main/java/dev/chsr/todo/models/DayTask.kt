package dev.chsr.todo.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "day_tasks")
data class DayTask(
    @PrimaryKey(autoGenerate = true)
    override val id: Int = 0,
    override val task: String,
    val day: Date,
) : Task