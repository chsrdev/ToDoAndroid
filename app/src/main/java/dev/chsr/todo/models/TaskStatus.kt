package dev.chsr.todo.models

enum class TaskStatus(val status: String) {
    COMPLETED("COMPLETED"),
    INCOMPLETE("INCOMPLETE"),
    DELETED("DELETED")
}