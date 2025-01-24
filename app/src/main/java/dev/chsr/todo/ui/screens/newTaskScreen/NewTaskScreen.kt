package dev.chsr.todo.ui.screens.newTaskScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.chsr.todo.ui.screens.newTaskScreen.components.AddTaskButton
import dev.chsr.todo.ui.screens.newTaskScreen.components.DropdownCategoryMenu
import dev.chsr.todo.ui.screens.newTaskScreen.components.TaskResetTimePicker
import dev.chsr.todo.ui.screens.newTaskScreen.components.TaskTextField
import dev.chsr.todo.viewmodels.TasksViewModel
import java.time.LocalTime

@Composable
fun NewTaskScreen(tasksViewModel: TasksViewModel = viewModel()) {
    val taskText = remember { mutableStateOf("") }
    val category = remember { mutableStateOf("Category") }
    val resetTime = remember { mutableStateOf(LocalTime.of(0, 0)) }
    val focusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current
    val addTaskButtonBackgroundColor = remember { mutableStateOf(Color.Black) }
    val interactionSource = remember { MutableInteractionSource() }

    Box(modifier = Modifier
        .fillMaxSize()
        .clickable(
            indication = null, interactionSource = interactionSource
        ) { focusManager.clearFocus() }) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .width(300.dp)
        ) {
            TaskTextField(
                taskText,
                Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                addTaskButtonBackgroundColor
            )
            Spacer(modifier = Modifier.height(8.dp))
            DropdownCategoryMenu(
                category,
                interactionSource,
                focusManager,
                addTaskButtonBackgroundColor
            )
            if (category.value == "Daily") {
                Spacer(modifier = Modifier.height(3.dp))
                TaskResetTimePicker(resetTime)
            }
            Spacer(modifier = Modifier.height(3.dp))
            AddTaskButton(
                taskText,
                category.value,
                resetTime.value,
                addTaskButtonBackgroundColor,
                tasksViewModel
            )
        }
    }
}