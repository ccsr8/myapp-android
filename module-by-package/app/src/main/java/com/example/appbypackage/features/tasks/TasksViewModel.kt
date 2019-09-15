package com.example.appbypackage.features.tasks

import androidx.lifecycle.ViewModel
import com.example.appbypackage.features.tasks.domain.GetTasksUseCase

class TasksViewModel(
private val getTasksUseCase: GetTasksUseCase
) : ViewModel(){
}