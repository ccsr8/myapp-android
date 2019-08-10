package myapp.features.arch_databinding


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import myapp.features.arch_databinding.databinding.FragmentDataBinding1StringBinding


class DataBinding1StringFragment : Fragment() {

    //region [event handlers]

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding: FragmentDataBinding1StringBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_data_binding1_string,
            container,
            false
        )

        binding.firstName = "first name binding"
        binding.lastName = "last name binding"

        return binding.root
    }

    //endregion

}
