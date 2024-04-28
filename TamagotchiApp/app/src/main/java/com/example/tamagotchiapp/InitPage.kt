package com.example.tamagotchiapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.prefs.AbstractPreferences

class InitPage : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    var petName : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init_page)

        val input = findViewById<EditText>(R.id.petNameIn)
        val btnSubmitName = findViewById<Button>(R.id.submitName)

        sharedPreferences = this.getSharedPreferences(
            "com.example.tamagotchiapp",
            Context.MODE_PRIVATE)
        petName = sharedPreferences.getString("pet_name", "")

        btnSubmitName.setOnClickListener{
            val newName = input.text.toString()
            if(newName == ""){
                Toast.makeText(this,"Please enter valid name", Toast.LENGTH_LONG).show()
            }else{
                sharedPreferences.edit().putString( "pet_name", newName).apply()

                val intent = Intent(this, PetInteraction::class.java)

                startActivity(intent)
            }
        }
    }
}