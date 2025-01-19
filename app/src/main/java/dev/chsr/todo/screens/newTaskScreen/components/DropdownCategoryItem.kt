package dev.chsr.todo.screens.newTaskScreen.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DropdownCategoryItem(
    category: String,
    iconId: Int,
    color: Color,
    choice: MutableState<String>,
    expanded: MutableState<Boolean>
) {
    DropdownMenuItem(
        text = {
            Row {
                Icon(
                    painter = painterResource(iconId),
                    contentDescription = "Dropdown Category $category",
                    tint = color,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    category, style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        },
        onClick = {
            choice.value = category
            expanded.value = false
        },
        interactionSource = remember { MutableInteractionSource() }
    )
}