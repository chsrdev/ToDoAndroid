package dev.chsr.todo.ui.screens.dailyTasksScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.chsr.todo.ui.screens.dailyTasksScreen.components.DailyTaskItem
import dev.chsr.todo.viewmodels.TasksViewModel

@Composable
fun DailyTasksScreen(tasksViewModel: TasksViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .offset(0.dp, 15.dp)
                .padding(bottom = 96.dp)
        ) {
            val tasks = tasksViewModel.getDailyTasks()
            items(tasks.size) { index ->
                DailyTaskItem(tasks[index], tasksViewModel)
            }
        }
    }
}
