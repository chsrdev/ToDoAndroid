package dev.chsr.todo.database

import androidx.room.TypeConverter
import dev.chsr.todo.models.TaskCategory
import dev.chsr.todo.models.TaskStatus
import java.util.Date

class Converters {
    @TypeConverter
    fun fromTaskStatus(status: TaskStatus): String {
        return status.status
    }

    @TypeConverter
    fun toTaskStatus(status: String): TaskStatus {
        return TaskStatus.valueOf(status)
    }

    @TypeConverter
    fun fromTaskCategory(category: TaskCategory): String {
        return category.category
    }

    @TypeConverter
    fun toTaskCategory(category: String): TaskCategory {
        return TaskCategory.valueOf(category)
    }

    @TypeConverter
    fun fromTimestamp(timestamp: Long): Date {
        return Date(timestamp)
    }

    @TypeConverter
    fun toTimestamp(date: Date): Long {
        return date.time
    }
}