package dev.chsr.todo.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.chsr.todo.viewmodels.TasksViewModel

@Composable
fun UpcomingTasksScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Upcoming Tasks",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}