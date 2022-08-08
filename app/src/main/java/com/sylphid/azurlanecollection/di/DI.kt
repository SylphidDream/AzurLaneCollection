package com.sylphid.azurlanecollection.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sylphid.azurlanecollection.api.ShipsRepositoryImpl
import com.sylphid.azurlanecollection.viewmodel.ShipViewModel
import kotlinx.coroutines.Dispatchers

object DI {
    private val database = Firebase.database

    fun provideDatabase(): FirebaseDatabase{
        return database
    }

    fun provideRepository() = ShipsRepositoryImpl(provideDatabase())
    fun provideDispatcher() = Dispatchers.IO

    fun provideViewModel(storeOwner: ViewModelStoreOwner):ShipViewModel{
        return ViewModelProvider(storeOwner, object: ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ShipViewModel(provideRepository(), provideDispatcher()) as T
            }
        })[ShipViewModel::class.java]
    }
}
