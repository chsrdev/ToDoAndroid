package dev.chsr.todo.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val task: String,
    val category: TaskCategory,
    val status: TaskStatus
)