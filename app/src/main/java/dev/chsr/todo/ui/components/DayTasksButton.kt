package dev.chsr.todo.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import dev.chsr.todo.R
import dev.chsr.todo.ui.theme.DarkMagenta

@Composable
fun DayTasksButton(navController: NavController) {
    TaskButton(
        backgroundColor = DarkMagenta,
        iconId = R.drawable.progress_icon,
        contentDescription = "Day Tasks Button Icon",
        targetDestination = "day",
        navController = navController
    )
}