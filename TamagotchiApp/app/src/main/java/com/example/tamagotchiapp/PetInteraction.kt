package com.example.tamagotchiapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import org.w3c.dom.Text

class PetInteraction : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_interaction)

        sharedPreferences = this.getSharedPreferences("com.example.tamagotchiapp",
            Context.MODE_PRIVATE)
        var petName = sharedPreferences.getString("pet_name", "")

        var petNameDis = findViewById<TextView>(R.id.petNameDis)

        petNameDis.text = petName

//        val timer = object: CountDownTimer(20000, 1000) {
//            override fun onTick(millisUntilFinished: Long) {}
//
//            override fun onFinish() {}
//        }
//        timer.start()

    }



}