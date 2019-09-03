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
import java.util.concurrent.ConcurrentMap

class TasksRepositoryImpl(
    private val tasksRemoteRepository: TasksRepository,
    private val tasksLocalDatasource: TasksLocalDatasource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : TasksRepository {

    private var cachedTasks: ConcurrentMap<String, TaskEntity>? = null

    override suspend fun getTasks(forceUpdate: Boolean): Result<List<TaskEntity>> {
        return withContext(ioDispatcher){
            if(!forceUpdate){
                cachedTasks?.let { cachedTasks ->
                    return@withContext Result.Success(cachedTasks.values.sortedBy { {it.id} })
                }
            }

            val newTask = fet
        }
    }

    //region [private members]

    private suspend fun fetchTasksFromRemoteOrLocal(forceUpdate: Boolean): Result<List<TaskEntity>>{
        // Remote first
        val remoteTasks = this.tasksRemoteRepository.getTasks()
        when(remoteTasks){
            is Error -> Timber.w("Remote data source featch failed")
            is Result.Success ->{
                this.refreshLocalDataSource(remoteTasks.data)
                return remoteTasks
            }
            else -> throw IllegalStateException()
        }

        // TODO forceUpdate

    }

    private suspend fun refreshLocalDataSource(tasks: List<TaskEntity>){
        this.tasksLocalDatasource.deleteAllTasks()
        for(task in tasks){
            this.tasksLocalDatasource.saveTask(task)
        }
    }

    //endregion

}