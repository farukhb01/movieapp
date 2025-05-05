package com.example.movieapp.core.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding


abstract class BaseFragment<Binding : ViewBinding>(
    private val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> Binding
) : Fragment() {

    private var _binding: Binding? = null
    val binding get() = _binding!!

    abstract fun setupViews()

    abstract fun onFragmentAttach()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onFragmentAttach()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
