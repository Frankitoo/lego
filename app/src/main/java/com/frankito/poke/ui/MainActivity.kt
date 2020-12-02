package com.frankito.poke.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.frankito.domain.models.toast.ToastData
import com.frankito.domain.models.toast.ToastDuration
import com.frankito.poke.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findNavController(R.id.nav_host_fragment).apply {
            viewModel.registerNavController(this)
        }

        viewModel.mainViewState()
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)

        viewModel.toast.observe(this, toastObserver)

        backButton.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }

    private fun handleState(viewState: MainViewState) {
        backButton.visibility = if (viewState.backButtonVisibility) View.VISIBLE else View.INVISIBLE
    }

    private val toastObserver by lazy {
        Observer<ToastData> { toast ->
            Toast.makeText(
                this, toast.message ?: "",
                if (toast.duration == ToastDuration.LONG) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
            ).show()
        }
    }
}