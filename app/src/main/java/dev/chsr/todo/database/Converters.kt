package dev.chsr.todo.database

import androidx.room.TypeConverter
import dev.chsr.todo.models.TaskStatus
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Date

class Converters {
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
    fun fromTimestamp(timestamp: Long): Date {
        return Date(timestamp)
    }

    @TypeConverter
    fun toTimestamp(date: Date): Long {
        return date.time
    }


    @TypeConverter
    fun fromLocalTime(localTime: LocalTime): String {
        return localTime.format(formatter)
    }

    @TypeConverter
    fun toLocalTime(timeString: String): LocalTime {
        return LocalTime.parse(timeString, formatter)
    }
}