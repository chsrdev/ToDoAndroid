package dev.chsr.todo.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.chsr.todo.database.AppDatabase
import dev.chsr.todo.models.Task
import dev.chsr.todo.models.TaskCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TasksViewModel(appDatabase: AppDatabase) : ViewModel() {
    private val taskDao = appDatabase.taskDao()
    private val tasks = MutableStateFlow<List<Task>>(emptyList())
    private val tasksByCategory = MutableStateFlow<HashMap<TaskCategory, List<Task>>>(hashMapOf())

    fun addTask(task: Task) {
        viewModelScope.launch {
            taskDao.insert(task)
            updateTasks()
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            taskDao.updateTask(task)
            updateTasks()
        }
    }

    fun updateTasks() {
        viewModelScope.launch {
            tasks.value = taskDao.getAllTasks()
            TaskCategory.entries.forEach { category ->
                tasksByCategory.value[category] = taskDao.getTasksByCategory(category.category)
            }
        }
    }

    fun getTasksByCategory(category: TaskCategory): List<Task> {
        return tasksByCategory.value[category] ?: emptyList()
    }
}