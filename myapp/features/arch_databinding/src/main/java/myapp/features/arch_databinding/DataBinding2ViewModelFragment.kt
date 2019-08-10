package myapp.features.arch_databinding


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import myapp.features.arch_databinding.data.SimpleViewModel
import myapp.features.arch_databinding.databinding.FragmentDataBinding2ViewModelBinding


class DataBinding2ViewModelFragment : Fragment() {

    //region [private memebers]

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(SimpleViewModel::class.java)
    }

    //endregion

    //region [event handlers]

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.fragment_data_binding2_view_model, container, false)

        var binding : FragmentDataBinding2ViewModelBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_data_binding2_view_model,
            container,
            false
        )

        binding.viewmodel = viewModel

        return binding.root
    }

    //endregion

}
