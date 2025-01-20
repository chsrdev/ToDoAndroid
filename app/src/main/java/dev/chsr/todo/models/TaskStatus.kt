package dev.chsr.todo.models

import androidx.compose.ui.graphics.Color
import dev.chsr.todo.ui.theme.DarkGreen
import dev.chsr.todo.ui.theme.DarkMagenta

enum class TaskStatus(val status: String, val color: Color) {
    COMPLETED("COMPLETED", DarkGreen),
    INCOMPLETE("INCOMPLETE", DarkMagenta),
    DELETED("DELETED", Color.Red)
}