package dev.chsr.todo.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object AppMigrations {
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("ALTER TABLE tasks ADD COLUMN completion_streak INTEGER DEFAULT 0 NOT NULL")
        }
    }

    val MIGRATION_2_3 = object : Migration(2, 3) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("DELETE FROM tasks WHERE category = 'IN_PROGRESS'")
        }
    }

    val MIGRATION_3_4 = object : Migration(3, 4) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("DELETE FROM tasks")
            db.execSQL("VACUUM")
            db.execSQL("ALTER TABLE tasks DROP category")
            db.execSQL("ALTER TABLE tasks RENAME TO daily_tasks")
            db.execSQL("CREATE TABLE upcoming_tasks (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, task TEXT NOT NULL)")
        }
    }

    val ALL_MIGRATIONS = arrayOf(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4)
}