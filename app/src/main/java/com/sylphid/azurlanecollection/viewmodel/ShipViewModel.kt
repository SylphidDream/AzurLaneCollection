package com.sylphid.azurlanecollection.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sylphid.azurlanecollection.api.ShipEntity
import com.sylphid.azurlanecollection.api.ShipsRepository
import com.sylphid.azurlanecollection.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

const val TAG = "ShipViewModel"
@HiltViewModel
class ShipViewModel @Inject constructor(
    private val repository: ShipsRepository,
    private val dispatcher: CoroutineDispatcher
): ViewModel() {

    private val _shipListData = MutableLiveData<UIState>()
    val shipListData: LiveData<UIState> get()=_shipListData

    private val _shipId = MutableLiveData<UIState>()
    val shipId: LiveData<UIState> get() = _shipId

    private val coroutineExceptionHandler by lazy {
        CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.e(TAG, "Context: $coroutineContext\nMessage: ${throwable.localizedMessage}",throwable)
        }
    }

    private val viewModelSafeScope by lazy{
        viewModelScope + coroutineExceptionHandler
    }

    fun getShips(){
        viewModelSafeScope.launch(dispatcher) {
            repository.getAllShips().collect {
                _shipListData.postValue(it)
            }
        }
    }

    fun setLoading(){
        _shipListData.value = UIState.Loading
        Log.d(TAG, "setLoading: ")
    }

    fun setLoadingForDetails(){
        _shipId.value = UIState.Loading
        Log.d(TAG, "setLoadingForDetails: ")
    }

    fun setSuccessForDetails(ship: ShipEntity){
        _shipId.value = UIState.Success(listOf(ship))
    }
}