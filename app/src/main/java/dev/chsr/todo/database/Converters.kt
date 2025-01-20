package dev.chsr.todo.database

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import dev.chsr.todo.models.TaskCategory
import dev.chsr.todo.models.TaskStatus
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Date

class Converters {
    @RequiresApi(Build.VERSION_CODES.O)
    private val formatter = DateTimeFormatter.ofPattern("HH:mm")

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


    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromLocalTime(localTime: LocalTime): String {
        return localTime.format(formatter)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toLocalTime(timeString: String): LocalTime {
        return LocalTime.parse(timeString, formatter)
    }
}