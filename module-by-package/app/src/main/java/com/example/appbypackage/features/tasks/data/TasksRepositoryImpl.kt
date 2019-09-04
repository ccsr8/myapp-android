package com.example.appbypackage.features.tasks.data

import com.example.appbypackage.data.Result
import com.example.appbypackage.features.tasks.data.local.TaskEntity
import com.example.appbypackage.features.tasks.data.local.TasksLocalDatasource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.lang.Error
import java.lang.IllegalStateException
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

class TasksRepositoryImpl(
    private val tasksRemoteRepository: TasksRepository,
    private val tasksLocalDatasource: TasksLocalDatasource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : TasksRepository {

    private var cachedTasks: ConcurrentMap<String, TaskEntity>? = null

    override suspend fun getTasks(forceUpdate: Boolean): Result<List<TaskEntity>> {
        return withContext(ioDispatcher) {
            if (!forceUpdate) {
                cachedTasks?.let { cachedTasks ->
                    return@withContext Result.Success(cachedTasks.values.sortedBy { it.id })
                }
            }

            val newTask = fetchTasksFromRemoteOrLocal(forceUpdate)

            (newTask as? Result.Success)?.let { refreshCache(it.data) }
        }
    }

    //region [private members]

    private suspend fun fetchTasksFromRemoteOrLocal(forceUpdate: Boolean): Result<List<TaskEntity>> {
        // Remote first
        val remoteTasks = this.tasksRemoteRepository.getTasks()
        when (remoteTasks) {
            is Error -> Timber.w("Remote data source fetch failed")
            is Result.Success -> {
                this.refreshLocalDataSource(remoteTasks.data)
                return remoteTasks
            }
            else -> throw IllegalStateException()
        }

        if (forceUpdate) {
            return Result.Error(Exception("Can't force refresh: remote data source is unavailable"))
        }

        // Local if remote fails
        val localTasks = this.tasksLocalDatasource.getTasks()
        if (localTasks is Result.Success) {
            return localTasks
        }

        return Result.Error(Exception("Error fetching from remote and local"))
    }

    private suspend fun refreshLocalDataSource(tasks: List<TaskEntity>) {
        this.tasksLocalDatasource.deleteAllTasks()
        for (task in tasks) {
            this.tasksLocalDatasource.saveTask(task)
        }
    }

    private fun refreshCache(tasks: List<TaskEntity>) {
        this.cachedTasks?.clear()
        tasks.sortedBy { it.id }.forEach {
            cacheAndPerform(it) {}
        }
    }

    private fun cacheTask(task: TaskEntity): TaskEntity {
        val cachedTask = TaskEntity(task.title, task.description, task.isCompleted, task.id)
        if (this.cachedTasks == null) {
            this.cachedTasks = ConcurrentHashMap()
        }

        this.cachedTasks?.put(cachedTask.id, cachedTask)
        return cachedTask
    }

    private inline fun cacheAndPerform(task: TaskEntity, perform: (TaskEntity) -> Unit) {
        val cachedTask = cacheTask(task)
        perform(cachedTask)
    }


    //endregion

}