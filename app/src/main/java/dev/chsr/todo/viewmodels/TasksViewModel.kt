package dev.chsr.todo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.chsr.todo.database.AppDatabase
import dev.chsr.todo.models.DailyTask
import dev.chsr.todo.models.Task
import dev.chsr.todo.models.TaskStatus
import dev.chsr.todo.models.UpcomingTask
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date
import java.util.concurrent.TimeUnit

class TasksViewModel(appDatabase: AppDatabase) : ViewModel() {
    private val dailyTaskDao = appDatabase.dailyTaskDao()
    private val upcomingTaskDao = appDatabase.upcomingTaskDao()
    private val upcomingTasks = MutableStateFlow<List<UpcomingTask>>(emptyList())
    private val dailyTasks = MutableStateFlow<List<DailyTask>>(emptyList())

    fun addTask(task: Task) {
        viewModelScope.launch {
            if(task is DailyTask) dailyTaskDao.insert(task)
            if(task is UpcomingTask) upcomingTaskDao.insert(task)
            updateTasks()
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            if(task is DailyTask) dailyTaskDao.delete(task)
            if(task is UpcomingTask) upcomingTaskDao.delete(task)
            updateTasks()
        }
    }

    private fun updateDailyTasksStatus() {
        dailyTasks.value.forEach { task ->
            if (task.status == TaskStatus.COMPLETED) {
                val resetDate = Date.from(
                    LocalDateTime.now()
                        .withHour(task.resetTime.hour)
                        .withMinute(task.resetTime.minute)
                        .withSecond(0)
                        .withNano(0)
                        .atZone(ZoneId.systemDefault())
                        .toInstant()
                )
                if (Date().after(resetDate) && task.completedAt.before(resetDate)) {
                    val timeBetween = resetDate.time - task.completedAt.time
                    val completionStreak =
                        if (TimeUnit.MILLISECONDS.toDays(timeBetween) > 0) 0
                        else task.completionStreak
                    updateTask(
                        DailyTask(
                            task.id,
                            task.task,
                            TaskStatus.INCOMPLETE,
                            task.completedAt,
                            task.resetTime,
                            completionStreak
                        )
                    )

                }
            }
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            if(task is DailyTask) dailyTaskDao.updateTask(task)
            if(task is UpcomingTask) upcomingTaskDao.updateTask(task)
            updateTasks()
        }
    }

    fun updateTasks() {
        viewModelScope.launch {
            dailyTasks.value = dailyTaskDao.getTasks()
            upcomingTasks.value = upcomingTaskDao.getTasks()
            updateDailyTasksStatus()
        }
    }

    fun getDailyTasks(): List<DailyTask> {
        viewModelScope.launch {
            updateTasks()
        }
        return dailyTasks.value
    }

    fun getUpcomingTasks(): List<UpcomingTask> {
        viewModelScope.launch {
            updateTasks()
        }
        return upcomingTasks.value
    }
}