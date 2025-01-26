package dev.chsr.todo.ui.screens.newTaskScreen.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.chsr.todo.models.DailyTask
import dev.chsr.todo.models.DayTask
import dev.chsr.todo.models.TaskCategory
import dev.chsr.todo.models.TaskStatus
import dev.chsr.todo.models.UpcomingTask
import dev.chsr.todo.ui.theme.DarkGreen
import dev.chsr.todo.viewmodels.TasksViewModel
import java.time.LocalTime
import java.util.Calendar
import java.util.Date

@Composable
fun AddTaskButton(
    taskText: MutableState<String>,
    dateText: MutableState<String>,
    category: String,
    resetTime: LocalTime,
    backgroundColor: MutableState<Color>,
    tasksViewModel: TasksViewModel
) {
    val dateRegex = """^([0]?[1-9]|[1][0-2])/([0]?[1-9]|[12][0-9]|3[01])/(\d{2}|\d{4})${'$'}""".toRegex()
    val bgColor by animateColorAsState(
        targetValue = backgroundColor.value,
        animationSpec = tween(
            durationMillis = 1000
        ),
        label = "Add task button color animation"
    )
    Button(
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = bgColor,
            contentColor = Color.White
        ),
        onClick = {
            if (category == "Category" || taskText.value.isEmpty() || (category == "Day" && !dateRegex.matches(
                    dateText.value
                ))
            ) {
                backgroundColor.value = Color.Red
                return@Button
            }
            backgroundColor.value = DarkGreen
            val taskCategory = TaskCategory.valueOf(category.uppercase())
            when (taskCategory) {
                TaskCategory.DAILY -> {
                    tasksViewModel.addTask(
                        DailyTask(
                            task = taskText.value,
                            status = TaskStatus.INCOMPLETE,
                            completedAt = Date(System.currentTimeMillis()),
                            resetTime = resetTime,
                            completionStreak = 0
                        )
                    )
                }

                TaskCategory.UPCOMING -> {
                    tasksViewModel.addTask(
                        UpcomingTask(
                            task = taskText.value
                        )
                    )
                }

                TaskCategory.DAY -> {
                    val x = dateText.value.split('/')
                    val year = if (x[2].toInt() >= 1000) x[2].toInt() else x[2].toInt()-2000
                    val calendar = Calendar.getInstance().apply {
                        set(Calendar.YEAR, year)
                        set(Calendar.MONTH, x[0].toInt() - 1)
                        set(Calendar.DAY_OF_MONTH, x[1].toInt())
                        set(Calendar.HOUR_OF_DAY, 0)
                        set(Calendar.MINUTE, 0)
                        set(Calendar.SECOND, 0)
                        set(Calendar.MILLISECOND, 0)
                    }

                    tasksViewModel.addTask(
                        DayTask(
                            task = taskText.value,
                            day = calendar.time
                        )
                    )
                }
            }
            taskText.value = ""
            dateText.value = ""
        },
        shape = RoundedCornerShape(8.dp)
    ) {
        Row {
            Text(
                "Add task",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterVertically),
                fontSize = 16.sp
            )
            Icon(Icons.Filled.Check, contentDescription = "Add Task Button Icon")
        }
    }
}