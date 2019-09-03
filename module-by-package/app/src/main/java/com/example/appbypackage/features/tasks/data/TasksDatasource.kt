package com.example.appbypackage.features.tasks.data

import com.example.appbypackage.data.Result
import com.example.appbypackage.features.tasks.data.local.TaskEntity

interface TasksDatasource {

    suspend fun getTasks(): Result<List<TaskEntity>>

    suspend fun getTask(taskId: String): Result<TaskEntity>

}