package myapp.features.login


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_login_authentication.*


class LoginAuthenticationFragment : Fragment() {

    //region [event handlers]

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login_authentication, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginNextButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_loginFragment_to_avatarFragment)
        }
    }

    //endregion

}
