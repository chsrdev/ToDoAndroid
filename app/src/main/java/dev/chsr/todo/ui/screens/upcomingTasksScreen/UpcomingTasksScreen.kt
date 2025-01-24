package dev.chsr.todo.ui.screens.upcomingTasksScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.chsr.todo.models.TaskCategory
import dev.chsr.todo.ui.screens.upcomingTasksScreen.components.UpcomingTaskItem
import dev.chsr.todo.viewmodels.TasksViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UpcomingTasksScreen(tasksViewModel: TasksViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .offset(0.dp, 15.dp)
                .padding(bottom = 96.dp)
        ) {
            val tasks = tasksViewModel.getUpcomingTasks()

            items(tasks.size) { index ->
                UpcomingTaskItem(tasks[index], tasksViewModel)
            }
        }
    }
}