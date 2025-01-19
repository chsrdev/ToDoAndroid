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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.chsr.todo.models.Task
import dev.chsr.todo.models.TaskCategory
import dev.chsr.todo.models.TaskStatus
import dev.chsr.todo.ui.screens.newTaskScreen.components.AddTaskButton
import dev.chsr.todo.ui.screens.newTaskScreen.components.DropdownCategoryMenu
import dev.chsr.todo.ui.screens.newTaskScreen.components.TaskTextField
import dev.chsr.todo.viewmodels.TasksViewModel

@Composable
fun NewTaskScreen(tasksViewModel: TasksViewModel = viewModel()) {
    val taskText = remember { mutableStateOf("") }
    val category = remember { mutableStateOf("Category") }
    val focusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current
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
                    .focusRequester(focusRequester)
            )
            Spacer(modifier = Modifier.height(8.dp))
            DropdownCategoryMenu(category, interactionSource, focusManager)
            Spacer(modifier = Modifier.height(3.dp))
            AddTaskButton(category, taskText, tasksViewModel)
        }
    }
}