package myapp.features.login


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_login_avatar.*
import myapp.libraries.actions.Actions


class LoginAvatarFragment : Fragment() {

    //region [event handlers]

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login_avatar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        avatarNextButton.setOnClickListener {
            activity!!.startActivity(Actions.openDashboardIntent(context!!))
            //startActivity(Actions.openDashboardIntent(context!!))
        }
    }

    //endregion

}