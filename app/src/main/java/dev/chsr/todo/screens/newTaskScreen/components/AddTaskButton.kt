package dev.chsr.todo.screens.newTaskScreen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddTaskButton() {
    Button(
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            contentColor = Color.White
        ),
        onClick = {},
        shape = RoundedCornerShape(8.dp)
    ) {
        Row {
            Text(
                "Add task",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterVertically),
                fontSize = 16.sp
            )
            Icon(Icons.Filled.Check, contentDescription = "Add Task Button Icon")
        }
    }
}