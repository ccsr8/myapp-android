package com.example.appbypackage.features.tasks.domain

import com.example.appbypackage.data.Result
import com.example.appbypackage.features.tasks.TasksFilterType
import com.example.appbypackage.features.tasks.data.TasksRepositoryImpl
import com.example.appbypackage.features.tasks.data.local.TaskEntity

class GetTasksUseCase(
    private val taskRepository: TasksRepositoryImpl
) {
    suspend operator fun invoke(
        forceUpdate: Boolean = false,
        currentFiltering: TasksFilterType = TasksFilterType.ALL_TASKS
    ): Result<List<TaskEntity>> {
        var tasksResult = taskRepository.getTasks(forceUpdate);

        if (tasksResult is Result.Success && currentFiltering != TasksFilterType.ALL_TASKS) {
            val tasks = tasksResult.data

            // TODO: taskToShow
        }


    }
}