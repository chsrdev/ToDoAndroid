package dev.chsr.todo.ui.screens.dayTasksScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SplitRow(
    text: String,
    color: Color
){
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 24.dp)){
        Box(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .background(color)
        )

        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 8.dp),
            fontWeight = FontWeight.Bold,
            color =color
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .background(color)
        )
    }
}