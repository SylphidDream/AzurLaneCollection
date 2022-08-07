package com.sylphid.azurlanecollection.api

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ktx.getValue
import com.sylphid.azurlanecollection.di.DI
import com.sylphid.azurlanecollection.model.UIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

interface ShipsRepository {
    suspend fun getAllShips(): Flow<UIState>
}


private val TAG = "ShipRepositoryImpl"
private val databaseRef = DI.provideDatabase().reference

private var ships = mutableListOf<ShipEntity>()
class ShipsRepositoryImpl : ShipsRepository {
    override suspend fun getAllShips(): Flow<UIState> =
        flow {
            try {
                databaseRef.child("ships").get().addOnSuccessListener { dataSnapshot: DataSnapshot ->
                        Log.d(TAG, "getAllShips: dataSnapshot:${dataSnapshot.children.toList().size}")
                        for (shipItem in dataSnapshot.children) {
                            ships.add(makeShip(shipItem))
                        }
                        Log.d(TAG, "getAllShips: Ships:${ships.size}")
                    }
                delay(5000)
                emit(UIState.Success(ships))

            } catch (e: Exception) {
                emit(UIState.Error(e))
            }
        }

    fun makeShip(input: DataSnapshot): ShipEntity{
        var ship =  ShipEntity(
            thumbnail = input.child("thumbnail").getValue(String::class.java),
            rarity = input.child("rarity").getValue(String::class.java),
            names= Name(
                en = input.child("names").child("en").getValue(String::class.java)
            )
        )
//        Log.d(TAG, "makeShip: \nthumbnail=${ship.thumbnail}\nname=${ship.names!!.en}")
        return ship
    }
}
