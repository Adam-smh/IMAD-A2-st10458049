package com.example.tamagotchiapp

import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import android.widget.ProgressBar
import com.google.gson.Gson

/*
Pet actions used to either interact or manage pet stats
 */

//range checker verifies if stats are within the 0 to 300 range
fun rangeChecker(petStatus: PetStatus): PetStatus {

    petStatus.hunger = outOfRangeStats(petStatus.hunger)
    petStatus.clean = outOfRangeStats(petStatus.clean)
    petStatus.boredom = outOfRangeStats(petStatus.boredom)
    return petStatus
}

//fun used to determine what is displayed to represent pet stats
fun checkStatus(stat: Int, barId: ProgressBar) :ResModel{

    var text: String
    var barNum = stat

    when{

         stat > 200 ->{
             //sets stat text
             text = "Good"
             //sets progress bar color
             barId.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
        }
        stat > 100  ->{
            text = "Okay"
            barId.setProgressTintList(ColorStateList.valueOf(Color.YELLOW));
        }
        else->{
            text = "Bad"
            barId.setProgressTintList(ColorStateList.valueOf(Color.RED));
        }
    }
    return ResModel(text, barNum)
}

fun petActionImgSet(action: String): Int {

    var imgState = R.drawable.artboard_1

    when(action){
        "eating" -> {
            imgState = R.drawable.artboard_8
        }
        "cleaning" -> {
            imgState = R.drawable.artboard_10_copy
        }
        "playing" -> {
            imgState = R.drawable.artboard_20
        }
    }

    return imgState
}

fun petIdolImgSet(petStatus: PetStatus): Int {

    val hunger = petStatus.hunger
    val dirty = petStatus.clean
    val bored = petStatus.boredom

    var imgStat: Int

    if(hunger < 100 && bored >= 100 && dirty >= 100){
        imgStat = R.drawable.artboard_7
    }else if(hunger >= 100 && bored < 100 && dirty >= 100){
        imgStat = R.drawable.artboard_6
    }else if(hunger >= 100 && bored >= 100 && dirty < 100){
        imgStat = R.drawable.artboard_10
    }else if(hunger < 150 && bored < 150 && dirty < 150) {
        imgStat = R.drawable.artboard_3
    }else{
        imgStat = R.drawable.artboard_1
    }

    return imgStat
}

//saves pets stats locally to ensure when app is reopened stats stay static
fun statSave(petStatus: PetStatus, sharedPreferences: SharedPreferences){

    val prefsEditor: SharedPreferences.Editor = sharedPreferences.edit()
    val gson = Gson()
    val json = gson.toJson(petStatus)
    prefsEditor.putString("PetStatus", json)
    prefsEditor.apply()
}

//fun used to name pet and save name locally
fun namePet(name: String, sharedPreferences: SharedPreferences){

    sharedPreferences.edit().putString( "pet_name", name).apply()

    val petStatus = PetStatus(
        hunger = 300,
        clean = 300,
        boredom = 300
    )
    val prefsEditor: SharedPreferences.Editor = sharedPreferences.edit()
    val gson = Gson()
    val json = gson.toJson(petStatus)
    prefsEditor.putString("PetStatus", json)
    prefsEditor.apply()
}

//fun used to feed pet
fun feedPet(petStatus: PetStatus): PetStatus {
    if (petStatus.hunger != 300){
        petStatus.hunger = petStatus.hunger + 100
        petStatus.clean = petStatus.clean - 50
    }
    rangeChecker(petStatus)
    return petStatus
}

//fun used to clean pet
fun cleanPet(petStatus: PetStatus): PetStatus {

    if(petStatus.clean != 300){
        petStatus.clean = 300
    }
    rangeChecker(petStatus)
    return petStatus
}

//fun used to play with pet
fun playPet(petStatus: PetStatus): PetStatus {
    if (petStatus.boredom != 300){
        petStatus.boredom = petStatus.boredom + 100
        petStatus.clean = petStatus.clean - 100
        petStatus.hunger = petStatus.hunger - 50
    }
    rangeChecker(petStatus)
    return petStatus
}
