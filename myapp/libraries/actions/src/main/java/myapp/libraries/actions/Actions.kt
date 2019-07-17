package myapp.libraries.actions

import android.content.Context
import android.content.Intent

object Actions {

    //region [public methods]

    fun openLoginIntent(context: Context) = internalIntent(context, "com.example.myapp.login.open")

    //endregion

    //region [private methods]

    private fun internalIntent(context: Context, action: String) = Intent(action).setPackage(context.packageName)

    //endregion
}