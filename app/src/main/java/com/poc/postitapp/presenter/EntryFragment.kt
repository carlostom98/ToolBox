package com.poc.postitapp.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.Scaffold
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment

import com.poc.postitapp.presenter.navigation.NavigationStack
import com.poc.postitapp.presenter.theme.AppTheme

class EntryFragment : Fragment() {

    private lateinit var activity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.activity = getActivity() as MainActivity
        return ComposeView(activity).apply {
            setContent {
                AppTheme {
                    Scaffold(
                        content = { paddingValues ->
                            NavigationStack(
                                manageDataViewModel = activity.manageDataViewModel,
                                paddingValues = paddingValues
                            )
                        })
                }
            }
        }
    }
}