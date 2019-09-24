package com.example.appbypackage.features.tasks


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels

import com.example.appbypackage.R
import com.example.appbypackage.databinding.FragmentTasksBinding

/**
 * A simple [Fragment] subclass.
 */
class TasksFragment : Fragment() {

    private val viewModel by viewModels<TasksViewModel>()
    // private val args: TasksFragmentArgs by navArgs()
    private lateinit var viewDataBinding: FragmentTasksBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tasks, container, false)
    }


}
