package com.frankito.presentation.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

abstract class BaseFragment<VM : BaseViewModel> : Fragment() {

    @get:LayoutRes
    abstract val layoutRes: Int

    abstract val viewModel: VM

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            arguments?.let { viewModel.arguments.value = it }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutRes, container, false).apply {
            setOnTouchListener { _, _ -> true }
        }
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.registerNavController(findNavController())
        setupViews()
    }

    protected open fun setupViews() {
        // No-op implementation
    }
}