package dev.chsr.todo.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import dev.chsr.todo.R
import dev.chsr.todo.ui.theme.DarkMagenta

@Composable
fun InProgressTasksButton(navController: NavController) {
    TaskButton(
        backgroundColor = DarkMagenta,
        iconId = R.drawable.progress_icon,
        contentDescription = "In Progress Tasks Button Icon",
        targetDestination = "inProgress",
        navController = navController
    )
}