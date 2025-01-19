package dev.chsr.todo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.chsr.todo.models.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}