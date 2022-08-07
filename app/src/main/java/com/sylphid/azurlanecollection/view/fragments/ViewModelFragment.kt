package com.sylphid.azurlanecollection.view.fragments

import androidx.fragment.app.Fragment
import com.sylphid.azurlanecollection.di.DI

open class ViewModelFragment: Fragment() {
    protected val viewModel by lazy {
        DI.provideViewModel(requireActivity())
    }
}