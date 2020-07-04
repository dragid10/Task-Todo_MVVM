package com.alexo.task_todomvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {
    private val TAG = TaskViewModel::class.java.canonicalName
    val taskList = MutableLiveData<MutableList<Task>>()

    fun deleteTask(currTask: Task) {
        val tasks: MutableList<Task>? = taskList.value
        tasks?.forEach { if (it.id == currTask.id) it.isDeleted = true }
        Log.d(TAG, "Task List: $tasks")
        taskList.value = tasks
    }

    fun addTask(task: String) {
        if (!task.isBlank()) {
            var tasks: MutableList<Task>? = taskList.value
            if (tasks == null) tasks = mutableListOf()
            val taskId = tasks.size
            val addedTask = Task(taskId, task.trim())
            val existsIndex = checkTaskExists(tasks, addedTask)
            if (existsIndex > -1) {
                tasks[existsIndex].isDeleted = false
                tasks[existsIndex].isCompleted = false
            } else {
                tasks.add(addedTask)

            }
            Log.d(TAG, "Task List: $tasks")
            taskList.value = tasks
        }
    }

    private fun checkTaskExists(
        tasks: MutableList<Task>?, addedTask: Task
    ): Int {
        var exists: Int = -1
        tasks?.let {
            for ((index, task) in tasks.withIndex()) {
                if (addedTask.details.trim().equals(task.details.trim(), true)) {
                    exists = index
                }
            }
        }
        return exists
    }
}