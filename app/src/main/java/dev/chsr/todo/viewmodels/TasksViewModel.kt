package dev.chsr.todo.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.chsr.todo.database.AppDatabase
import dev.chsr.todo.models.Task
import dev.chsr.todo.models.TaskCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TasksViewModel(appDatabase: AppDatabase) : ViewModel() {
    private val taskDao = appDatabase.taskDao()
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    private val _tasksByCategory = MutableStateFlow<HashMap<TaskCategory, List<Task>>>(hashMapOf())
    val tasks: StateFlow<List<Task>> = _tasks
    val tasksByCategory: StateFlow<HashMap<TaskCategory, List<Task>>> = _tasksByCategory

    fun addTask(task: Task) {
        viewModelScope.launch {
            taskDao.insert(task)
            _tasksByCategory.value[task.category] = taskDao.getTasksByCategory(task.category.category)
        }
    }

    fun updateTasks() {
        viewModelScope.launch {
            _tasks.value = taskDao.getAllTasks()
            TaskCategory.entries.forEach { category ->
                _tasksByCategory.value[category] = taskDao.getTasksByCategory(category.category)
            }
        }
    }

    fun getTasksByCategory(category: TaskCategory): List<Task> {
        return tasksByCategory.value[category] ?: emptyList()
    }

    fun printTasks() {
        viewModelScope.launch {
            taskDao.getAllTasks().forEach { task ->
                Log.i("task", "${task.id} ${task.task} ${task.category} ${task.status}")
            }
        }
    }
}