package dev.chsr.todo.ui.screens.newTaskScreen.components

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun TaskTextField(taskText: MutableState<String>, modifier: Modifier) {
    OutlinedTextField(
        value = taskText.value,
        onValueChange = { taskText.value = it },
        label = { Text("Task") },
        modifier = modifier,
        textStyle = TextStyle(
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            shadow = Shadow(Color.Black),
            fontSize = 16.sp,
            textAlign = TextAlign.Start
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.DarkGray,
            unfocusedBorderColor = Color.DarkGray,
            errorBorderColor = Color.Red,
            cursorColor = Color.Black,
            focusedLabelColor = Color.DarkGray,
            unfocusedLabelColor = Color.DarkGray,
        )
    )
}