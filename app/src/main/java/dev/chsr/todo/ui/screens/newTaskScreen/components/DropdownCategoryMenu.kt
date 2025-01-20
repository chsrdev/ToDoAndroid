package dev.chsr.todo.ui.screens.newTaskScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.chsr.todo.R
import dev.chsr.todo.ui.theme.DarkBlue
import dev.chsr.todo.ui.theme.DarkGreen
import dev.chsr.todo.ui.theme.DarkMagenta


@Composable
fun DropdownCategoryMenu(
    choice: MutableState<String>,
    interactionSource: MutableInteractionSource,
    focusManager: FocusManager
) {
    val expanded = remember { mutableStateOf(false) }
    val icon = if (expanded.value) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
    DropdownCategoryButton(expanded, choice, icon, interactionSource, focusManager)
    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = { expanded.value = false },
        modifier = Modifier
            .background(Color.White)
            .width(300.dp)
            .border(color = Color.Black, width = 2.dp, shape = RoundedCornerShape(8.dp))
    ) {
        DropdownCategoryItem("Daily", R.drawable.calendar_icon, DarkGreen, choice, expanded)
        DropdownCategoryItem("Upcoming", R.drawable.timer_icon, DarkBlue, choice, expanded)
        DropdownCategoryItem("In progress", R.drawable.progress_icon, DarkMagenta, choice, expanded)
    }
}