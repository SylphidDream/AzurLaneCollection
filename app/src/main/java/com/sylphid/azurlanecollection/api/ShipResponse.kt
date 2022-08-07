package com.sylphid.azurlanecollection.api

data class ShipResponse(
    var ships: List<ShipEntity>? = null,
    var exception: Exception? = null
)
