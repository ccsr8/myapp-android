package com.example.appbypackage.features.tasks.data.remote

import com.example.appbypackage.data.Result
import com.example.appbypackage.features.tasks.data.TasksDatasource
import com.example.appbypackage.features.tasks.data.local.TaskEntity
import kotlinx.coroutines.delay

object TasksRemoteDataSource : TasksDatasource {

    private const val SERVICE_LATNECY_IN_MILLIS = 2000L

    private var TASKS_SERVICE_DATA = LinkedHashMap<String, TaskEntity>(2)

    init{
        this.addTask("Title#1", "description 1")
        this.addTask("Title#2", "description 2")
    }

    override suspend fun getTasks(): Result<List<TaskEntity>> {
        val tasks = TASKS_SERVICE_DATA.values.toList()
        delay(this.SERVICE_LATNECY_IN_MILLIS)

        return Result.Success(tasks)
    }

    override suspend fun getTask(taskId: String): Result<TaskEntity> {
        delay(this.SERVICE_LATNECY_IN_MILLIS)
        this.TASKS_SERVICE_DATA[taskId]?.let {
            return Result.Success(it)
        }

        return Result.Error(Exception("Task not found"))
    }

    override suspend fun deleteAllTasks() {
        this.TASKS_SERVICE_DATA.clear()
    }

    override suspend fun saveTask(task: TaskEntity) {
        this.TASKS_SERVICE_DATA.put(task.id, task)
    }

    //region [private methods]

    private fun addTask(title: String, description: String){
        val newTask = TaskEntity(title, description)
        this.TASKS_SERVICE_DATA.put(newTask.id, newTask)
    }

    //endregion

}