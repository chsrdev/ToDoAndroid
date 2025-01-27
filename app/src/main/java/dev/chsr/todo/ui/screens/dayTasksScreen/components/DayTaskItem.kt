package dev.chsr.todo.ui.screens.dayTasksScreen.components

import androidx.compose.foundation.layout.Column
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
import dev.chsr.todo.models.DayTask
import dev.chsr.todo.viewmodels.TasksViewModel
import java.text.SimpleDateFormat
import java.util.Locale


@Composable
fun DayTaskItem(task: DayTask, tasksViewModel: TasksViewModel) {
    val isDeleted = remember { mutableStateOf(false) }
    val dateFormat = SimpleDateFormat("MMM dd yyyy", Locale.getDefault())
    if (isDeleted.value)
        return

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            contentColor = Color.Black,
            containerColor = Color.White
        ),
        onClick = {
            tasksViewModel.deleteTask(task)
            isDeleted.value = true
        }
    ) {
        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 2.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = task.task,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = dateFormat.format(task.day),
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

