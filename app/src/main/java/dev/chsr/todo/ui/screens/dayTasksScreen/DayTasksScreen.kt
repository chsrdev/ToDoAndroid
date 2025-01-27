package dev.chsr.todo.ui.screens.dayTasksScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.chsr.todo.models.DayTask
import dev.chsr.todo.ui.screens.dayTasksScreen.components.DayTaskItem
import dev.chsr.todo.ui.screens.dayTasksScreen.components.SplitRow
import dev.chsr.todo.ui.theme.DarkBlue
import dev.chsr.todo.ui.theme.DarkGreen
import dev.chsr.todo.ui.theme.DarkMagenta
import dev.chsr.todo.viewmodels.TasksViewModel
import java.time.LocalDate
import java.time.ZoneId


@Composable
fun DayTasksScreen(tasksViewModel: TasksViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .offset(0.dp, 15.dp)
                .padding(bottom = 96.dp)
        ) {
            val tasks = tasksViewModel.getDayTasks().sortedBy { it.day }
            val now = LocalDate.now()
            val tasksByDate = LinkedHashMap<Pair<String, Color>, List<DayTask>>()
            tasksByDate[Pair("Outdated", DarkMagenta)] = tasks.filter {
                val taskDate = it.day.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                taskDate.isBefore(now)
            }
            tasksByDate[Pair("Today", DarkGreen)] = tasks.filter {
                val taskDate = it.day.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                taskDate == now
            }
            tasksByDate[Pair("Future", DarkBlue)] = tasks.filter {
                val taskDate = it.day.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                taskDate.isAfter(now)
            }

            tasksByDate.forEach { entry ->
                if (entry.value.isNotEmpty()) {
                    item {
                        SplitRow(entry.key.first, entry.key.second)
                    }
                    entry.value.forEach { task ->
                        item {
                            DayTaskItem(task, tasksViewModel)
                        }
                    }
                }
            }
        }
    }
}