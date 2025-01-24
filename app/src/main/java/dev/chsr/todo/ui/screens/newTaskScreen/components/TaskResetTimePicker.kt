package dev.chsr.todo.ui.screens.newTaskScreen.components

import android.app.TimePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TaskResetTimePicker(resetTime: MutableState<LocalTime>) {
    val context = LocalContext.current
    val formatter = DateTimeFormatter.ofPattern("HH:mm")

    Column {
        Button(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp),
            onClick = {
                val timePickerDialog = TimePickerDialog(
                    context,
                    { _, hour, minute ->
                        resetTime.value = LocalTime.of(hour, minute)
                    },
                    0,
                    0,
                    false
                )
                timePickerDialog.show()
            }) {
            Text(
                text = "Select Time [${resetTime.value.format(formatter)}]",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}