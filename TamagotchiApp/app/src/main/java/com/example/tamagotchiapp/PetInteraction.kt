package com.example.tamagotchiapp

import android.content.Context
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.gson.Gson

class PetInteraction : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_interaction)

        //img placeHolder
        var actImg: Int

        //declare btn val
        val feedBtn = findViewById<Button>(R.id.feedBtn)
        val cleanBtn = findViewById<Button>(R.id.cleanBtn)
        val playBtn = findViewById<Button>(R.id.playBtn)

        //declare text val
        val hungerStat = findViewById<TextView>(R.id.hungerStatus)
        val dirtyStat = findViewById<TextView>(R.id.dirtyStatus)
        val boredomStat = findViewById<TextView>(R.id.boredomStatus)
        val petNameDis = findViewById<TextView>(R.id.petNameDis)

        //declare progress bar val
        val hungerBar = findViewById<ProgressBar>(R.id.hungerBar)
        val stinkyBar = findViewById<ProgressBar>(R.id.stinkyBar)
        val boredBar = findViewById<ProgressBar>(R.id.boredBar)

        //cat image dec
        val catImg = findViewById<ImageView>(R.id.catImg)

        //sharedPreferences used to store data locally
        val sharedPreferences: SharedPreferences = this.getSharedPreferences("com.example.tamagotchiapp",
            Context.MODE_PRIVATE)

        //fetching petStatus from local storage using json
        val gson = Gson()
        val json: String? = sharedPreferences.getString("PetStatus", "")
        val petStatus: PetStatus = gson.fromJson(json, PetStatus::class.java)

        //fetching petName from local storage
        var petName = sharedPreferences.getString("pet_name", "")

        //displaying Pets name
        petNameDis.text = petName

        //function used for displaying stats
        fun statDis(petStatus: PetStatus){
            var hungerRes = checkStatus(petStatus.hunger, hungerBar)
            var dirtyRes = checkStatus(petStatus.clean, stinkyBar)
            var boredRes = checkStatus(petStatus.boredom, boredBar)
            var idolImgSet = petIdolImgSet(petStatus)

            //setting idol image state
            catImg.setImageDrawable(ContextCompat.getDrawable(this, idolImgSet))

            //stat text
            hungerStat.text = hungerRes.text
            dirtyStat.text = dirtyRes.text
            boredomStat.text = boredRes.text

            //stat progress bar
            hungerBar.progress = hungerRes.barNum
            stinkyBar.progress = dirtyRes.barNum
            boredBar.progress = boredRes.barNum
        }
        statDis(petStatus)

        fun petImgAni(petStatus: PetStatus, actImg: Int){

            catImg.setImageDrawable(ContextCompat.getDrawable(this, actImg))

            val aniTimer = object: CountDownTimer(2800, 10000) {
                override fun onTick(millisUntilFinished: Long) {
                }

                override fun onFinish() {
                    statDis(petStatus)
                    statSave(petStatus, sharedPreferences)
                }
            }
            aniTimer.start()
        }

        //timer used to depreciate stats over time
        val timer = object: CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {

                if (petStatus.hunger != 0){
                    petStatus.hunger = petStatus.hunger - 50
                }
                if (petStatus.clean != 0){
                    petStatus.clean = petStatus.clean - 50
                }
                if (petStatus.boredom != 0){
                    petStatus.boredom = petStatus.boredom - 50
                }
                statDis(petStatus)
                statSave(petStatus, sharedPreferences)
                this.start()
            }
        }
        timer.start()

        //button used to feed pet
        feedBtn.setOnClickListener{
            if (petStatus.hunger != 300) {
                var res = feedPet(petStatus)
                actImg = petActionImgSet("eating")
                petImgAni(res, actImg)
                timer.cancel()
                timer.start()
            }else{
                Toast.makeText(this,"$petName is not hungry right now", Toast.LENGTH_LONG).show()
            }
        }

        //button used to clean pet
        cleanBtn.setOnClickListener{
            if (petStatus.clean != 300){
                var res = cleanPet(petStatus)
                actImg = petActionImgSet("cleaning")
                petImgAni(res, actImg)
                timer.cancel()
                timer.start()
            }else{
                Toast.makeText(this,"$petName does not need a bath right now", Toast.LENGTH_LONG).show()
            }
        }

        //-button used to play with pet
        playBtn.setOnClickListener{
            if (petStatus.boredom != 300){
                var res = playPet(petStatus)
                actImg = petActionImgSet("playing")
                petImgAni(res, actImg)
                timer.cancel()
                timer.start()
            }else{
                Toast.makeText(this,"$petName does not wanna play right now", Toast.LENGTH_LONG).show()
            }
        }
    }
}