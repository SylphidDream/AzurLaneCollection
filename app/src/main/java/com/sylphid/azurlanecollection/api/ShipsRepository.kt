package com.sylphid.azurlanecollection.api

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.sylphid.azurlanecollection.model.UIState
import dagger.Provides
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


interface ShipsRepository {
    suspend fun getAllShips(): Flow<UIState>
}


private val TAG = "ShipRepositoryImpl"

private var ships = mutableListOf<ShipEntity>()
private var finishedRun = false

class ShipsRepositoryImpl @Inject constructor(private val database: FirebaseDatabase) : ShipsRepository {
    override suspend fun getAllShips(): Flow<UIState> =
        flow {
            ships.clear()
            finishedRun = false
            try {
                var task = database.reference.child("ships").get()
                while (!finishedRun) {
                    if (task.isComplete) {
                        finishedRun = true
                    }
                }
                var dataSnapshot = task.getResult()

                Log.d(
                    TAG,
                    "getAllShips: dataSnapshot:${dataSnapshot.children.toList().size}"
                )
                for (shipItem in dataSnapshot.children) {
                    ships.add(makeShip(shipItem))
                }
                Log.d(TAG, "getAllShips: Ships:${ships.size}")


            } catch (e: Exception) {
                emit(UIState.Error(e))
            } finally {
                Log.d(TAG, "getAllShips: Reached Finally Block")
                if (finishedRun) {
                    emit(UIState.Success(ships))
                }
            }
        }

    fun makeShip(input: DataSnapshot): ShipEntity {
        var skinRef = input.child("skins")
        var statsRef = input.child("stats")
        var skillsRef = input.child("skills")

        var skinsList = mutableListOf<Skin>()
        var stats: Stats
        var skills = mutableListOf<Skill>()

        for (skin in skinRef.children) {
            skinsList.add(
                Skin(
                    name = skin.child("name").getValue(String::class.java),
                    image = skin.child("image").getValue(String::class.java),
                    background = skin.child("background").getValue(String::class.java),
                    chibi = skin.child("chibi").getValue(String::class.java)
                )
            )
        }

        var baseStats = makeStatDetails(statsRef.child("baseStats"))
        var level100 = makeStatDetails(statsRef.child("level100"))
        var level120 = makeStatDetails(statsRef.child("level120"))
        var level125 = makeStatDetails(statsRef.child("level125"))
        if (input.hasChild("retrofit")) {
            var level100Retrofit = makeStatDetails(statsRef.child("level100Retrofit"))
            var level120Retrofit = makeStatDetails(statsRef.child("level120Retrofit"))
            var level125Retrofit = makeStatDetails(statsRef.child("level125Retrofit"))
            stats = Stats(
                baseStats = baseStats,
                level100 = level100,
                level120 = level120,
                level125 = level125,
                level100Retrofit = level100Retrofit,
                level120Retrofit = level120Retrofit,
                level125Retrofit = level125Retrofit
            )
        } else {
            stats = Stats(
                baseStats = baseStats,
                level100 = level100,
                level120 = level120,
                level125 = level125
            )
        }

        for (skill in skillsRef.children) {
            var names = Name(
                en = skill.child("names").child("en").getValue(String::class.java)
            )
            skills.add(
                Skill(
                    icon = skill.child("icon").getValue(String::class.java),
                    names = names,
                    description = skill.child("description").getValue(String::class.java),
                    color = skill.child("color").getValue(String::class.java)
                )
            )
        }


        var ship = ShipEntity(
            thumbnail = input.child("thumbnail").getValue(String::class.java),
            rarity = input.child("rarity").getValue(String::class.java),
            names = Name(
                en = input.child("names").child("en").getValue(String::class.java),
                code = input.child("names").child("code").getValue(String::class.java)
            ),
            hullType = input.child("hullType").getValue(String::class.java),
            retrofit = input.child("retrofit").getValue(Boolean::class.java),
            retrofitHullType = input.child("retrofitHullType").getValue(String::class.java) ?: null,
            //skins
            skins = skinsList.toList(),
            //stats
            stats = stats,
            //skills
            skills = skills
        )
//        Log.d(TAG, "makeShip: \nthumbnail=${ship.thumbnail}\nname=${ship.names!!.en}")
        return ship
    }

    fun makeStatDetails(statDetails: DataSnapshot): StatDetails {
        return StatDetails(
            health = statDetails.child("health").getValue(String::class.java),
            armor = statDetails.child("armor").getValue(String::class.java),
            reload = statDetails.child("reload").getValue(String::class.java),
            luck = statDetails.child("luck").getValue(String::class.java),
            firepower = statDetails.child("firepower").getValue(String::class.java),
            torpedo = statDetails.child("torpedo").getValue(String::class.java),
            evasion = statDetails.child("evasion").getValue(String::class.java),
            speed = statDetails.child("speed").getValue(String::class.java),
            antiair = statDetails.child("antiair").getValue(String::class.java),
            aviation = statDetails.child("aviation").getValue(String::class.java),
            oilConsumption = statDetails.child("oilConsumption").getValue(String::class.java),
            accuracy = statDetails.child("accuracy").getValue(String::class.java),
            antisubmarineWarfare = statDetails.child("antisubmarineWarfare")
                .getValue(String::class.java),
            oxygen = statDetails.child("oxygen").getValue(String::class.java),
            ammunition = statDetails.child("ammunition").getValue(String::class.java),
        )
    }
}
