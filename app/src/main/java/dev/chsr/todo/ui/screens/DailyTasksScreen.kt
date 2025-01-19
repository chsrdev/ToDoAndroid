package dev.chsr.todo.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import dev.chsr.todo.models.Task
import dev.chsr.todo.models.TaskCategory
import dev.chsr.todo.viewmodels.TasksViewModel

@Composable
fun DailyTasksScreen(tasksViewModel: TasksViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        tasksViewModel.printTasks()
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            tasksViewModel.getTasksByCategory(TaskCategory.DAILY).forEach { task ->
                Log.i("task", task.task)
                Text(
                    text = task.task,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}