package dev.chsr.todo.ui.screens.newTaskScreen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun DropdownCategoryButton(
    expanded: MutableState<Boolean>,
    choice: MutableState<String>,
    icon: ImageVector,
    interactionSource: MutableInteractionSource,
    addTaskBackgroundColor: MutableState<Color>,
    focusManager: FocusManager
) {
    Button(
        onClick = {
            expanded.value = !expanded.value
            addTaskBackgroundColor.value = Color.Black
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .border(
                width = 1.5.dp,
                color = Color.Black,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(
                indication = null, interactionSource = interactionSource
            ) { focusManager.clearFocus() },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = choice.value,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                ),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Icon(icon, contentDescription = "Task Category Dropdown")
        }
    }
}