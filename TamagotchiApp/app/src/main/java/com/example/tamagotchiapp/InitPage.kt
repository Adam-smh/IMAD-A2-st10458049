package com.example.tamagotchiapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson


class InitPage : AppCompatActivity() {

    private var petName : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init_page)

        //declarations
        val input = findViewById<EditText>(R.id.petNameIn)
        val btnSubmitName = findViewById<Button>(R.id.submitName)

        //sharedPreferences used to store data locally
        var sharedPreferences: SharedPreferences = this.getSharedPreferences(
            "com.example.tamagotchiapp",
            Context.MODE_PRIVATE)
        petName = sharedPreferences.getString("pet_name", "")

        btnSubmitName.setOnClickListener{
            val newName = input.text.toString()
            if(newName == ""){
                Toast.makeText(this,"Please enter valid name", Toast.LENGTH_LONG).show()
            }else{
                namePet(newName, sharedPreferences)

                val intent = Intent(this, PetInteraction::class.java)

                startActivity(intent)
            }
        }
    }
}