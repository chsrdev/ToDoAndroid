package dev.chsr.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.chsr.todo.components.DailyTasksButton
import dev.chsr.todo.components.InProgressTasksButton
import dev.chsr.todo.components.NewTaskButton
import dev.chsr.todo.components.UpcomingTasksButton
import dev.chsr.todo.screens.DailyTasksScreen
import dev.chsr.todo.screens.InProgressTasksScreen
import dev.chsr.todo.screens.newTaskScreen.NewTaskScreen
import dev.chsr.todo.screens.UpcomingTasksScreen
import dev.chsr.todo.ui.theme.ToDoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoTheme {
                val navController = rememberNavController()
                Box(modifier = Modifier.fillMaxSize()) {
                    NavHost(navController = navController, startDestination = "daily") {
                        composable(route = "new") { NewTaskScreen() }
                        composable(route = "daily") { DailyTasksScreen() }
                        composable(route = "upcoming") { UpcomingTasksScreen() }
                        composable(route = "inProgress") { InProgressTasksScreen() }
                    }
                    Row(
                        modifier = Modifier.align(Alignment.BottomCenter),
                    ) {
                        NewTaskButton(navController)
                        DailyTasksButton(navController)
                        UpcomingTasksButton(navController)
                        InProgressTasksButton(navController)
                    }
                }
            }
        }
    }
}
