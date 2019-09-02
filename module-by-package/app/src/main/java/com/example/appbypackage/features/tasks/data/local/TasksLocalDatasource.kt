package com.example.appbypackage.features.tasks.data.local

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class TasksLocalDatasource internal constructor(
private val tasksDao: TasksDao,
private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
){
}