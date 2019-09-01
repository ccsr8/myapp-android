package com.example.appbypackage.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appbypackage.features.tasks.data.local.TasksDao
import com.example.appbypackage.features.tasks.data.local.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao(): TasksDao

}