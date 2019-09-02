package com.example.appbypackage.features.tasks.data.local

import androidx.room.*

@Dao
interface TasksDao {

    @Query("select * from tasks")
    suspend fun getTasks(): List<TaskEntity>

    @Query("select * from tasks where entryid = :taskId")
    suspend fun getTaskById(taskId: String): TaskEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity)

    @Update
    suspend fun updateTask(task: TaskEntity): Int

    @Query("update tasks set completed = :completed where entryid = :taskId")
    suspend fun updateCompleted(taskId: String, completed: Boolean)

    @Query("delete from tasks where entryid = :taskId")
    suspend fun deleteTaskById(taskId: String): Int

    @Query("delete from tasks")
    suspend fun deleteTasks()

    @Query("delete from tasks where completed = 1")
    suspend fun deleteCompletedTasks(): Int
}