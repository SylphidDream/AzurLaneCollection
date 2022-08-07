package com.sylphid.azurlanecollection.model

import com.sylphid.azurlanecollection.api.ShipEntity

sealed class UIState {
    object Loading: UIState()
    class Error(val error: Exception): UIState()
    class Success(var response:List<ShipEntity>): UIState()
}
