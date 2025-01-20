package dev.chsr.todo.viewmodels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.chsr.todo.database.AppDatabase
import dev.chsr.todo.models.Task
import dev.chsr.todo.models.TaskCategory
import dev.chsr.todo.models.TaskStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

class TasksViewModel(appDatabase: AppDatabase) : ViewModel() {
    private val taskDao = appDatabase.taskDao()
    private val tasks = MutableStateFlow<List<Task>>(emptyList())
    private val tasksByCategory = MutableStateFlow<HashMap<TaskCategory, List<Task>>>(hashMapOf())

    @RequiresApi(Build.VERSION_CODES.O)
    fun addTask(task: Task) {
        viewModelScope.launch {
            taskDao.insert(task)
            updateTasks()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateDailyTasksStatus() {
        tasks.value.forEach { task ->
            if (task.category == TaskCategory.DAILY && task.status == TaskStatus.COMPLETED) {
                val resetDate = Date.from(
                    LocalDateTime.now()
                        .withHour(task.resetTime.hour)
                        .withMinute(task.resetTime.minute)
                        .withSecond(0)
                        .withNano(0)
                        .atZone(ZoneId.systemDefault())
                        .toInstant()
                )
                if (Date().after(resetDate) && task.completedAt.before(resetDate))
                    updateTask(
                        Task(
                            task.id,
                            task.task,
                            task.category,
                            TaskStatus.INCOMPLETE,
                            task.completedAt,
                            task.resetTime
                        )
                    )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateTask(task: Task) {
        viewModelScope.launch {
            taskDao.updateTask(task)
            updateTasks()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateTasks() {
        viewModelScope.launch {
            tasks.value = taskDao.getAllTasks()
            updateDailyTasksStatus()
            TaskCategory.entries.forEach { category ->
                tasksByCategory.value[category] = taskDao.getTasksByCategory(category.category)
            }
        }
    }

    fun getTasksByCategory(category: TaskCategory): List<Task> {
        return tasksByCategory.value[category] ?: emptyList()
    }
}