package dev.chsr.todo.models

enum class TaskCategory(val category: String) {
    DAILY("DAILY"),
    UPCOMING("UPCOMING"),
    IN_PROGRESS("IN_PROGRESS")
}