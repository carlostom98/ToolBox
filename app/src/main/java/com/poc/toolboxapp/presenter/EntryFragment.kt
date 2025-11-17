package com.poc.toolboxapp.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment

import com.poc.toolboxapp.presenter.navigation.NavigationStack
import com.poc.toolboxapp.presenter.screens.molecules.TopBar
import com.poc.toolboxapp.presenter.theme.AppTheme

class EntryFragment : Fragment() {

    private lateinit var activity: MainActivity

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.activity = getActivity() as MainActivity
        return ComposeView(activity).apply {
            setContent {
                AppTheme {
                    Scaffold(
                        topBar = {
                            val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
                            TopBar(modifier = Modifier.wrapContentHeight(), scrollBehavior)
                        },
                        content = { paddingValues ->
                            Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
                                NavigationStack(
                                    paddingValues = paddingValues
                                )
                            }
                        })
                }
            }
        }
    }
}