package com.example.tamagotchiapp

import android.content.SharedPreferences
import com.google.gson.Gson


//outOfRangeStats ensures that pet stats stays within the 1-3 range
fun outOfRangeStats(petStatus: PetStatus): PetStatus {

    if (petStatus.hunger > 3){
        petStatus.hunger = 3
    }
    if (petStatus.boredom > 3){
        petStatus.boredom = 3
    }
    if (petStatus.dirty > 3){
        petStatus.dirty = 3
    }
    return petStatus
}

fun statSave(petStatus: PetStatus, sharedPreferences: SharedPreferences){

    val prefsEditor: SharedPreferences.Editor = sharedPreferences.edit()
    val gson = Gson()
    val json = gson.toJson(petStatus)
    prefsEditor.putString("PetStatus", json)
    prefsEditor.apply()
}

fun namePet(name: String, sharedPreferences: SharedPreferences){

    sharedPreferences.edit().putString( "pet_name", name).apply()

    val petStatus : PetStatus = PetStatus(
        hunger = 1,
        dirty = 1,
        boredom = 1
    )
    val prefsEditor: SharedPreferences.Editor = sharedPreferences.edit()
    val gson = Gson()
    val json = gson.toJson(petStatus)
    prefsEditor.putString("PetStatus", json)
    prefsEditor.apply()
}

fun feedPet(petStatus: PetStatus): PetStatus {

    outOfRangeStats(petStatus)
    if (petStatus.hunger != 1){
        petStatus.hunger--
        petStatus.dirty++
    }
    return petStatus
}

fun cleanPet(petStatus: PetStatus): PetStatus {

    outOfRangeStats(petStatus)
    if(petStatus.dirty != 1){
        petStatus.dirty = 1
    }
    return petStatus
}

fun playPet(petStatus: PetStatus): PetStatus {

    outOfRangeStats(petStatus)
    if (petStatus.boredom != 1){
        petStatus.boredom--
        petStatus.dirty++
        petStatus.hunger++
    }

    return petStatus
}
