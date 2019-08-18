package myapp.libraries.actions

import android.content.Context
import android.content.Intent

object Actions {

    //region [public methods]

    fun openLoginIntent(context: Context) = internalIntent(context, "com.example.myapp.login.open")

    fun openDashboardIntent(context: Context) = internalIntent(context, "com.example.myapp.dashboard.open")

    fun openDataBindingIntent(context: Context) = internalIntent(context, "com.example.myapp.databinding.open")

    fun openRoomIntent(context: Context) = internalIntent(context, "com.example.myapp.room.open")

    //endregion

    //region [private methods]

    private fun internalIntent(context: Context, action: String) = Intent(action).setPackage(context.packageName)

    //endregion
}