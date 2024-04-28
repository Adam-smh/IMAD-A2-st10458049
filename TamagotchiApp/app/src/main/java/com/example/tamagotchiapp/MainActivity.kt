package com.example.tamagotchiapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val titleHome = findViewById<TextView>(R.id.homeTitle)
        val initBtn = findViewById<Button>(R.id.initBtn)

        sharedPreferences = this.getSharedPreferences(
            "com.example.tamagotchiapp",
            Context.MODE_PRIVATE)
            var petName = sharedPreferences.getString("pet_name", "")

        if(petName != ""){

            titleHome.text = "Welcome Back, \n$petName \nmisses you"

            initBtn.text = "Continue"
            initBtn.setOnClickListener{
                val intent = Intent(this, PetInteraction::class.java)
                startActivity(intent)
            }

        }else{
            initBtn.setOnClickListener {
                val intent = Intent(this, InitPage::class.java)

                startActivity(intent)
            }
        }

    }
}