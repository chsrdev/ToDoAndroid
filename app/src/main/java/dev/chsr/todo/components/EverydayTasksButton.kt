package dev.chsr.todo.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import dev.chsr.todo.R
import dev.chsr.todo.ui.theme.DarkBlue

@Composable
fun EverydayTasksButton(navController: NavController) {
    TaskButton(
        backgroundColor = DarkBlue,
        iconId = R.drawable.calendar_icon,
        contentDescription = "Everyday Tasks Button Icon",
        targetDestination = "everyday",
        navController = navController
    )
}