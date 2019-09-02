package myapp.libraries.appdb.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import myapp.libraries.appdb.data.source.entity.AppConfigEntity

@Database(entities = [AppConfigEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appConfigDao(): AppConfigDao

}