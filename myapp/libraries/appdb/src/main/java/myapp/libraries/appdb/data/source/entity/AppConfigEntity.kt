package myapp.libraries.appdb.data.source.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "AppConfig")
data class AppConfigEntity(

    var title: String,
    var description: String

) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}