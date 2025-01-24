package dev.chsr.todo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.chsr.todo.models.DailyTask
import dev.chsr.todo.models.UpcomingTask

@Database(entities = [DailyTask::class, UpcomingTask::class], version = 4, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dailyTaskDao(): DailyTaskDao
    abstract fun upcomingTaskDao(): UpcomingTaskDao

    companion object {
        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app-database"
            ).addMigrations(*AppMigrations.ALL_MIGRATIONS)
                .build()
        }
    }
}