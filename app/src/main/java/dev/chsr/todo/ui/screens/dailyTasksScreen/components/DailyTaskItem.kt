package dev.chsr.todo.ui.screens.dailyTasksScreen.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.chsr.todo.models.DailyTask
import dev.chsr.todo.models.TaskStatus
import dev.chsr.todo.viewmodels.TasksViewModel
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DailyTaskItem(_task: DailyTask, tasksViewModel: TasksViewModel) {
    val taskStatusString = remember { mutableStateOf("") }
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    val isSettingsOpened = remember { mutableStateOf(false) }
    val isDeleted = remember { mutableStateOf(false) }
    var task = _task

    if (isDeleted.value)
        return

    fun updateTaskStatusString() {
        taskStatusString.value = task.status.status.lowercase().replaceFirstChar { it.uppercase() }
        val calendar = Calendar.getInstance()
        calendar.time = task.completedAt
        val completionHours = calendar.get(Calendar.HOUR_OF_DAY)
        val completionMinutes = calendar.get(Calendar.MINUTE)

        if (task.status == TaskStatus.COMPLETED)
            taskStatusString.value += " at ${
                LocalTime.of(completionHours, completionMinutes).format(formatter)
            }"
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .combinedClickable(
                onLongClick = {
                    isSettingsOpened.value = !isSettingsOpened.value
                },
                onClick = {
                    if (task.status != TaskStatus.COMPLETED) {
                        val resetDate = Date.from(
                            LocalDateTime
                                .now()
                                .withHour(task.resetTime.hour)
                                .withMinute(task.resetTime.minute)
                                .withSecond(0)
                                .withNano(0)
                                .atZone(ZoneId.systemDefault())
                                .toInstant()
                        )
                        val currentDate = Date(System.currentTimeMillis())
                        val timeBetween = resetDate.time - currentDate.time
                        val completionStreak =
                            if (TimeUnit.MILLISECONDS.toDays(timeBetween) == 0L) task.completionStreak + 1
                            else 0
                        task = DailyTask(
                            task.id,
                            task.task,
                            TaskStatus.COMPLETED,
                            currentDate,
                            task.resetTime,
                            completionStreak
                        )
                        tasksViewModel.updateTask(task)
                        updateTaskStatusString()
                    }
                }
            ),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            contentColor = Color.Black,
            containerColor = Color.White
        ),
    ) {
        if (isSettingsOpened.value) {
            DailyTaskItemSettings(task, isDeleted, tasksViewModel)
            return@Card
        }
        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 2.dp)
        ) {
            updateTaskStatusString()
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = task.task,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Streak: ${task.completionStreak}",
                    color = Color(151, 98, 34, 255),
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
                Text(
                    text = taskStatusString.value,
                    color = task.status.color,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                )
                Text(
                    text = "Reset: ${
                        LocalTime.of(task.resetTime.hour, task.resetTime.minute)
                            .format(formatter)
                    }",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                )
            }
        }
    }
}
