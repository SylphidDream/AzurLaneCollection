package com.sylphid.azurlanecollection.di.hilt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sylphid.azurlanecollection.api.ShipsRepository
import com.sylphid.azurlanecollection.api.ShipsRepositoryImpl
import com.sylphid.azurlanecollection.viewmodel.ShipViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HiltModule {

    @Singleton
    @Provides
    fun provideDatabase(): FirebaseDatabase {
        return Firebase.database
    }

    @Singleton
    @Provides
    fun provideRepository(): ShipsRepository = ShipsRepositoryImpl(provideDatabase())

    @Singleton
    @Provides
    fun provideDispatcher() = Dispatchers.IO

}