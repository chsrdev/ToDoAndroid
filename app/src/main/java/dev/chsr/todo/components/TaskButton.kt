package dev.chsr.todo.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun TaskButton(
    backgroundColor: Color,
    iconId: Int,
    contentDescription: String,
    targetDestination: String,
    navController: NavController
) {
    IconButton(
        onClick = {
            if (!navController.currentDestination?.route.equals(targetDestination)) {
                navController.navigate(targetDestination)
            }
        },
        modifier = Modifier
            .padding(16.dp)
            .background(color = backgroundColor, shape = RoundedCornerShape(8.dp))
    ) {
        Image(
            painter = painterResource(id = iconId),
            contentDescription = contentDescription,
            modifier = Modifier.padding(8.dp)
        )
    }
}