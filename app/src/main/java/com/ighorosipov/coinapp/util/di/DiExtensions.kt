package com.ighorosipov.coinapp.util.di

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

inline fun <reified T : ViewModel> AppCompatActivity.lazyViewModel(
    noinline create: (stateHandle: SavedStateHandle) -> T,
) = viewModels<T> {
    ViewModelFactory(this, create)
}

inline fun <reified T : ViewModel> Fragment.lazyViewModel(
    noinline create: (stateHandle: SavedStateHandle) -> T,
) = viewModels<T> {
    ViewModelFactory(this, create)
}