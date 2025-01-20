package dev.chsr.todo.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import dev.chsr.todo.R
import dev.chsr.todo.ui.theme.DarkBlue

@Composable
fun UpcomingTasksButton(navController: NavController) {
    TaskButton(
        backgroundColor = DarkBlue,
        iconId = R.drawable.timer_icon,
        contentDescription = "Upcoming Tasks Button Icon",
        targetDestination = "upcoming",
        navController = navController
    )
}