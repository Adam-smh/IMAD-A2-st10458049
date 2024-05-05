package com.example.tamagotchiapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //declarations
        val titleHome = findViewById<TextView>(R.id.homeTitle)
        val initBtn = findViewById<Button>(R.id.initBtn)

        //sharedPreferences used to store data locally
        var sharedPreferences: SharedPreferences = this.getSharedPreferences(
            "com.example.tamagotchiapp",
            Context.MODE_PRIVATE)

        //fetching petName from local storage
        var petName = sharedPreferences.getString("pet_name", "")

        /*if Pets name has already been set,
        home screen will be displayed along with pet img and name.
        Button redirects user to PetInteraction screen*/
        if(petName != ""){

            titleHome.text = "Welcome Back,\n$petName \nmisses you"
            initBtn.text = "Continue"
            initBtn.setOnClickListener{
                val intent = Intent(this, PetInteraction::class.java)
                startActivity(intent)
            }

        /*If name has not been set, initial welcome screen will be displayed.
        Button will redirect user to InitPage*/
        }else{
            titleHome.text = "Welcome to PetSim, \n you have a new pet!"
            initBtn.setOnClickListener {
                val intent = Intent(this, InitPage::class.java)

                startActivity(intent)
            }
        }

    }
}