package dev.chsr.todo.ui.screens.newTaskScreen.components

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.chsr.todo.models.Task
import dev.chsr.todo.models.TaskCategory
import dev.chsr.todo.models.TaskStatus
import dev.chsr.todo.ui.theme.DarkGreen
import dev.chsr.todo.viewmodels.TasksViewModel
import java.time.LocalTime
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddTaskButton(
    taskText: String,
    category: String,
    resetTime: LocalTime,
    backgroundColor: MutableState<Color>,
    tasksViewModel: TasksViewModel
) {
    val bgColor by animateColorAsState(
        targetValue = backgroundColor.value,
        animationSpec = tween(
            durationMillis = 1000
        )
    )
    Button(
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = bgColor,
            contentColor = Color.White
        ),
        onClick = {
            if (category == "Category" || taskText.isEmpty()) {
                backgroundColor.value = Color.Red
                return@Button
            }
            backgroundColor.value = DarkGreen
            tasksViewModel.addTask(
                Task(
                    task = taskText,
                    category = TaskCategory.valueOf(
                        category.uppercase().split(" ").joinToString("_")
                    ),
                    status = TaskStatus.INCOMPLETE,
                    completedAt = Date(System.currentTimeMillis()),
                    resetTime = resetTime
                )
            )
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