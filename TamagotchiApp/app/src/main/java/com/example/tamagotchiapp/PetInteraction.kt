package com.example.tamagotchiapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import org.w3c.dom.Text


class PetInteraction : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_interaction)

        val feedBtn = findViewById<Button>(R.id.feedBtn)
        val cleanBtn = findViewById<Button>(R.id.cleanBtn)
        val playBtn = findViewById<Button>(R.id.playBtn)

        val hungerStat = findViewById<TextView>(R.id.hungerStatus)
        val dirtyStat = findViewById<TextView>(R.id.dirtyStatus)
        val boredomStat = findViewById<TextView>(R.id.boredomStatus)

        sharedPreferences = this.getSharedPreferences("com.example.tamagotchiapp",
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

        feedBtn.setOnClickListener{
            var res = feedPet(petStatus)
            checkStatus(res)
        }
        cleanBtn.setOnClickListener{
            var res =cleanPet(petStatus)
            checkStatus(res)
        }
        playBtn.setOnClickListener{
            var res = playPet(petStatus)
            checkStatus(res)
        }
    }




}