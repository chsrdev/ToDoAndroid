package dev.chsr.todo.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.chsr.todo.database.AppDatabase
import dev.chsr.todo.ui.components.DailyTasksButton
import dev.chsr.todo.ui.components.DayTasksButton
import dev.chsr.todo.ui.components.NewTaskButton
import dev.chsr.todo.ui.components.UpcomingTasksButton
import dev.chsr.todo.ui.screens.dailyTasksScreen.DailyTasksScreen
import dev.chsr.todo.ui.screens.dayTasksScreen.DayTasksScreen
import dev.chsr.todo.ui.screens.newTaskScreen.NewTaskScreen
import dev.chsr.todo.ui.screens.upcomingTasksScreen.UpcomingTasksScreen
import dev.chsr.todo.ui.theme.ToDoTheme
import dev.chsr.todo.viewmodels.TasksViewModel

class MainActivity : ComponentActivity() {
    private lateinit var appDatabase: AppDatabase

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appDatabase = AppDatabase.getInstance(this)
        val tasksViewModel = TasksViewModel(appDatabase)
        tasksViewModel.updateTasks()
        
        enableEdgeToEdge()

        setContent {
            ToDoTheme {
                Box(modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.displayCutout)) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "new") {
                        composable(route = "new") { NewTaskScreen(tasksViewModel) }
                        composable(route = "daily") { DailyTasksScreen(tasksViewModel) }
                        composable(route = "upcoming") { UpcomingTasksScreen(tasksViewModel) }
                        composable(route = "day") { DayTasksScreen(tasksViewModel) }
                    }
                    Row(
                        modifier = Modifier.align(Alignment.BottomCenter),
                    ) {
                        NewTaskButton(navController)
                        DailyTasksButton(navController)
                        UpcomingTasksButton(navController)
                        DayTasksButton(navController)
                    }
                }
            }
        }
    }
}
