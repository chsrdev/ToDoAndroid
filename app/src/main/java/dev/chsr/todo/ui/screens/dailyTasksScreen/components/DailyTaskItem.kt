package dev.chsr.todo.ui.screens.dailyTasksScreen.components

import android.os.Build
import androidx.annotation.RequiresApi
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
import dev.chsr.todo.models.Task
import dev.chsr.todo.models.TaskStatus
import dev.chsr.todo.viewmodels.TasksViewModel
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DailyTaskItem(_task: Task, tasksViewModel: TasksViewModel) {
    val taskStatusString = remember { mutableStateOf("") }
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    var task = _task

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
            .padding(horizontal = 16.dp, vertical = 12.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            contentColor = Color.Black,
            containerColor = Color.White
        ),
        onClick = {
            if (task.status != TaskStatus.COMPLETED) {
                task = Task(
                    task.id,
                    task.task,
                    task.category,
                    TaskStatus.COMPLETED,
                    Date(System.currentTimeMillis()),
                    task.resetTime
                )
                tasksViewModel.updateTask(task)
                updateTaskStatusString()
            }
        }
    ) {
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
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = taskStatusString.value,
                    color = task.status.color,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp),
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

