package com.example.appbypackage.features.tasks.data.local

import com.example.appbypackage.data.Result
import com.example.appbypackage.features.tasks.data.TasksDatasource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TasksLocalDatasource internal constructor(
    private val tasksDao: TasksDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : TasksDatasource {

    override suspend fun getTasks(): Result<List<TaskEntity>> = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(tasksDao.getTasks())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getTask(taskId: String): Result<TaskEntity> = withContext(ioDispatcher){
        try {
            val task = tasksDao.getTaskById(taskId)
            if (task != null){
                return@withContext Result.Success(task)
            } else {
                return@withContext Result.Error(Exception("Task not found."))
            }
        }
        catch (e: Exception){
            return@withContext Result.Error(e)
        }
    }


}