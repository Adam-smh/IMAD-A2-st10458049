package com.example.tamagotchiapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val checkTextIfWorking = findViewById<TextView>(R.id.checkingText)
        sharedPreferences = this.getSharedPreferences(
            "com.example.tamagotchiapp",
            Context.MODE_PRIVATE)
            var petName = sharedPreferences.getString("pet_name", "")

        if(petName != ""){

            val intent = Intent(this, PetInteraction::class.java)

            startActivity(intent)

        }else{
            val intent = Intent(this, InitPage::class.java)

            startActivity(intent)
        }

    }
}