package com.example.appbypackage.features.tasks.data

import com.example.appbypackage.data.Result
import com.example.appbypackage.features.tasks.data.local.TaskEntity
import com.example.appbypackage.features.tasks.data.local.TasksLocalDatasource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import kotlin.IllegalStateException

class TasksRepositoryImpl(
    private val tasksRemoteDataSource: TasksDatasource,
    private val tasksLocalDatasource: TasksDatasource,
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

            val newTasks = fetchTasksFromRemoteOrLocal(forceUpdate)

            (newTasks as? Result.Success)?.let { refreshCache(it.data) }

            cachedTasks?.values?.let { tasks ->
                return@withContext Result.Success(tasks.sortedBy { it.id })
            }

            (newTasks as? Result.Success)?.let {
                if (it.data.isEmpty()) {
                    return@withContext Result.Success(it.data)
                }
            }

            return@withContext Result.Error(Exception("Illegal state"))
        }
    }

    override suspend fun getTask(taskId: String, forceUpdate: Boolean): Result<TaskEntity> {
        return withContext(ioDispatcher) {
            if (!forceUpdate) {
                getTaskWithId(taskId)?.let {
                    return@withContext Result.Success(it)
                }
            }

            val newTask = fetchTaskFromRemoteOrLocal(taskId, forceUpdate)
            (newTask as? Result.Success)?.let { cacheTask((it.data)) }

            return@withContext newTask
        }
    }

    //region [private members]

    private suspend fun fetchTaskFromRemoteOrLocal(
        taskId: String,
        forceUpdate: Boolean
    ): Result<TaskEntity> {
        val remoteTask = tasksRemoteDataSource.getTask(taskId)
        when (remoteTask) {
            is Result.Error -> Timber.w("Remote data source fetch failed")
            is Result.Success -> {
                refreshLocalDataSource(remoteTask.data)
                return remoteTask
            }
            else -> throw IllegalStateException()
        }

        if (forceUpdate) {
            return Result.Error(Exception("Refresh failed"))
        }

        val localTasks = this.tasksLocalDatasource.getTask(taskId)
        if (localTasks is Result.Success) return localTasks

        return Result.Error(Exception("Error fetching from remote and local"))
    }

    private suspend fun fetchTasksFromRemoteOrLocal(forceUpdate: Boolean): Result<List<TaskEntity>> {
        // Remote first
        val remoteTasks = this.tasksRemoteDataSource.getTasks()
        when (remoteTasks) {
            is Result.Error -> Timber.w("Remote data source fetch failed")
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

    private suspend fun refreshLocalDataSource(task: TaskEntity) {
        tasksLocalDatasource.saveTask(task)
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

    private fun getTaskWithId(id: String) = cachedTasks?.get(id)


    //endregion

}