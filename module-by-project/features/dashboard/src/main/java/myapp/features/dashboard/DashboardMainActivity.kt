package myapp.features.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class DashboardMainActivity : AppCompatActivity() {

    //region [event handlers]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_main)

        showFragment(DashboardMainFragment())
    }

    //endregion

    //region [private methods]

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    //endregion
}
