package dev.chsr.todo.ui.screens.dailyTasksScreen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.chsr.todo.models.DailyTask
import dev.chsr.todo.ui.theme.DarkMagenta
import dev.chsr.todo.viewmodels.TasksViewModel

@Composable
fun DailyTaskItemSettings(task: DailyTask, isDeleted: MutableState<Boolean>, tasksViewModel: TasksViewModel) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.White
            ),
            modifier = Modifier.align(Alignment.Center),
            shape = RoundedCornerShape(8.dp),
            onClick = {
                tasksViewModel.deleteTask(task)
                isDeleted.value = true
            },
        ) {
            Text(
                text = "Delete",
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
        }
    }
}