package dev.chsr.todo.ui.screens.newTaskScreen.components

import android.app.DatePickerDialog
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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar


@Composable
fun DatePicker(selectedDate: MutableState<LocalDate>) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val formatter = DateTimeFormatter.ofPattern("MMM dd yyyy")

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    Column {
        Button(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp),
            onClick = {
                DatePickerDialog(
                    context,
                    { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                        selectedDate.value = LocalDate.now()
                            .withYear(selectedYear)
                            .withMonth(selectedMonth+1)
                            .withDayOfMonth(selectedDayOfMonth)

                    },
                    year,
                    month,
                    day
                ).show()
            }) {
            Text(
                text = selectedDate.value.format(formatter),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}