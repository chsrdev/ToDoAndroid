package dev.chsr.todo.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "upcoming_tasks")
data class UpcomingTask (
    @PrimaryKey(autoGenerate = true)
    override val id: Int = 0,
    override val task: String,
): Task