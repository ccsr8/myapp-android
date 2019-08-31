package myapp.features.arch_databinding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_data_binding_main.*


class DataBindingMainFragment : Fragment() {

    //region [event handlers]

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_data_binding_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingStringButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_dataBindingMainFragment2_to_dataBinding1StringFragment2)
        }

        bindingViewModelButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_dataBindingMainFragment2_to_dataBinding2ViewModelFragment)
        }
    }

    //endregion

}
