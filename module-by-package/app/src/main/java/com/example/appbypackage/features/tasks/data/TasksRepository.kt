package com.example.appbypackage.features.tasks.data

import com.example.appbypackage.data.Result
import com.example.appbypackage.features.tasks.data.local.TaskEntity

interface TasksRepository {

    suspend fun getTasks(forceUpdate: Boolean = false): Result<List<TaskEntity>>

    suspend fun getTask(taskId: String, forceUpdate: Boolean = false): Result<TaskEntity>

//    suspend fun saveTask(task: TaskEntity)
//
//    suspend fun completeTask(task: TaskEntity)
//
//    suspend fun completeTask(taskId: String)
//
//    suspend fun activateTask(task: TaskEntity)
//
//    suspend fun activateTask(taskId: String)
//
//    suspend fun clearCompletedTasks()
//
//    suspend fun deleteAllTasks()
//
//    suspend fun deleteTask(taskId: String)

}