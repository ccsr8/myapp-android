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

            var tasksToShow = mutableListOf<TaskEntity>()
            for (task in tasks) {
                when (currentFiltering) {
                    TasksFilterType.ACTIVE_TASKS -> if (task.isActive) {
                        tasksToShow.add(task)
                    }
                    TasksFilterType.COMPLETED_TASKS -> if (task.isCompleted) {
                        tasksToShow.add(task)
                    }
                    else -> NotImplementedError()
                }
            }

            return Result.Success(tasksToShow)
        }

        return tasksResult

    }
}