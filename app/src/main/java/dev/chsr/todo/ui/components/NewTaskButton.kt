package dev.chsr.todo.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import dev.chsr.todo.R

@Composable
fun NewTaskButton(navController: NavController) {
    TaskButton(
        backgroundColor = Color.Black,
        iconId = R.drawable.add_icon,
        contentDescription = "New Task Button Icon",
        targetDestination = "new",
        navController = navController
    )
}