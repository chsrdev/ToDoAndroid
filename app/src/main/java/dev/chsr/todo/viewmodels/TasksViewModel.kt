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
    val tasks: StateFlow<List<Task>> = _tasks

    fun addTask(task: Task) {
        viewModelScope.launch {
            taskDao.insert(task)
        }
    }

    fun getTasksByCategory(category: TaskCategory) {
        viewModelScope.launch {
            taskDao.getTasksByCategory(category.category)
        }
    }
//    fun printTasks() {
//        viewModelScope.launch {
//            taskDao.getAllTasks().forEach { task ->
//                Log.i("task", "${task.id} ${task.task} ${task.category} ${task.status}")
//            }
//        }
//    }
}