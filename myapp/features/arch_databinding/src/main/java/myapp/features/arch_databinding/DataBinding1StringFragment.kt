package myapp.features.arch_databinding


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import myapp.features.arch_databinding.databinding.FragmentDataBinding1StringBinding


class DataBinding1StringFragment : Fragment() {

    //region

    //private lateinit var viewDataBinding: DataBinding1StringFragmentBinding

    //endregion

    //region [event handlers]

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.fragment_data_binding1_string, container, false)

        var binding : FragmentDataBinding1StringBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_data_binding1_string,
            container ,
            false)

        binding.firstName = "first name binding"
        binding.lastName = "last name binding"

        return binding.root
    }

    //endregion

}
