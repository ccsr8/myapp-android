package myapp.libraries.appdb.data.source.local

import androidx.room.Dao
import androidx.room.Query
import myapp.libraries.appdb.data.source.entity.AppConfigEntity

@Dao
interface AppConfigDao {

    @Query("SELECT * FROM AppConfig")
    suspend fun getConfigs(): List<AppConfigEntity>

}