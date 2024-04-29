package com.example.tamagotchiapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson

class PetInteraction : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_interaction)

        val feedBtn = findViewById<Button>(R.id.feedBtn)
        val cleanBtn = findViewById<Button>(R.id.cleanBtn)
        val playBtn = findViewById<Button>(R.id.playBtn)

        val hungerStat = findViewById<TextView>(R.id.hungerStatus)
        val dirtyStat = findViewById<TextView>(R.id.dirtyStatus)
        val boredomStat = findViewById<TextView>(R.id.boredomStatus)

        var sharedPreferences: SharedPreferences = this.getSharedPreferences("com.example.tamagotchiapp",
            Context.MODE_PRIVATE)

        val gson = Gson()
        val json: String? = sharedPreferences.getString("PetStatus", "")
        val petStatus: PetStatus = gson.fromJson(json, PetStatus::class.java)

        var petName = sharedPreferences.getString("pet_name", "")

        var petNameDis = findViewById<TextView>(R.id.petNameDis)

        petNameDis.text = petName

        fun checkStatus(petStats: PetStatus){

            when(petStats.hunger){

                1 ->{
                    hungerStat.text = "Not Hungry"
                }
                2 ->{
                    hungerStat.text = "Hungry"
                }
                3 ->{
                    hungerStat.text = "Starving"
                }
            }

            when(petStats.dirty){

                1 ->{
                    dirtyStat.text = "Clean"
                }
                2 ->{
                    dirtyStat.text = "Messy"
                }
                3 ->{
                    dirtyStat.text = "Dirty"
                }
            }

            when(petStats.boredom){
                1 ->{
                    boredomStat.text = "Happy"
                }
                2 ->{
                    boredomStat.text = "Okay"
                }
                3 ->{
                    boredomStat.text = "Bored"
                }
            }

        }
        checkStatus(petStatus)

        fun statSave(petStatus: PetStatus){

            var sharedPreferences: SharedPreferences = this.getSharedPreferences("com.example.tamagotchiapp",
                Context.MODE_PRIVATE)

            val prefsEditor: SharedPreferences.Editor = sharedPreferences.edit()
            val gson = Gson()
            val json = gson.toJson(petStatus)
            prefsEditor.putString("PetStatus", json)
            prefsEditor.apply()
        }

        val timer = object: CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                if (petStatus.hunger != 3){
                    petStatus.hunger++
                }
                if (petStatus.dirty != 3){
                    petStatus.dirty++
                }
                if (petStatus.boredom != 3){
                    petStatus.boredom++
                }
                checkStatus(petStatus)
                statSave(petStatus)
                this.start()
            }
        }
        timer.start()

        feedBtn.setOnClickListener{
            if (petStatus.hunger != 1) {
                var res = feedPet(petStatus)
                checkStatus(res)
                statSave(res)
                timer.cancel()
                timer.start()
            }else{
                Toast.makeText(this,"$petName is not hungry right now", Toast.LENGTH_LONG).show()
            }
        }
        cleanBtn.setOnClickListener{
            if (petStatus.dirty != 1){
                var res = cleanPet(petStatus)
                    checkStatus(res)
                    statSave(res)
                    timer.cancel()
                    timer.start()
            }else{
                Toast.makeText(this,"$petName does not need a bath right now", Toast.LENGTH_LONG).show()
            }

        }
        playBtn.setOnClickListener{
            if (petStatus.boredom != 1){
                var res = playPet(petStatus)
                checkStatus(res)
                statSave(res)
                timer.cancel()
                timer.start()
            }else{
                Toast.makeText(this,"$petName does not wanna play right now", Toast.LENGTH_LONG).show()
            }
        }
    }
}