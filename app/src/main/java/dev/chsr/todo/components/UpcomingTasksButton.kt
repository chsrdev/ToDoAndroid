package dev.chsr.todo.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import dev.chsr.todo.R
import dev.chsr.todo.ui.theme.DarkGreen

@Composable
fun UpcomingTasksButton(navController: NavController) {
    TaskButton(
        backgroundColor = DarkGreen,
        iconId = R.drawable.timer_icon,
        contentDescription = "Upcoming Tasks Button Icon",
        targetDestination = "upcoming",
        navController = navController
    )
}