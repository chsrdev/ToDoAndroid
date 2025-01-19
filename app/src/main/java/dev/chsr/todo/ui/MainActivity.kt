package dev.chsr.todo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import dev.chsr.todo.database.AppDatabase
import dev.chsr.todo.ui.components.DailyTasksButton
import dev.chsr.todo.ui.components.InProgressTasksButton
import dev.chsr.todo.ui.components.NewTaskButton
import dev.chsr.todo.ui.components.UpcomingTasksButton
import dev.chsr.todo.ui.screens.DailyTasksScreen
import dev.chsr.todo.ui.screens.InProgressTasksScreen
import dev.chsr.todo.ui.screens.newTaskScreen.NewTaskScreen
import dev.chsr.todo.ui.screens.UpcomingTasksScreen
import dev.chsr.todo.ui.theme.ToDoTheme
import dev.chsr.todo.viewmodels.TasksViewModel

class MainActivity : ComponentActivity() {
    lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoTheme {
                val navController = rememberNavController()
                appDatabase = Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java,
                    "tasks"
                ).build()
                val tasksViewModel = TasksViewModel(appDatabase)
                Box(modifier = Modifier.fillMaxSize()) {
                    NavHost(navController = navController, startDestination = "daily") {
                        composable(route = "new") { NewTaskScreen(tasksViewModel) }
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
