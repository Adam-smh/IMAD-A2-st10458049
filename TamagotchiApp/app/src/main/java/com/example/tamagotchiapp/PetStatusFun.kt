package com.example.tamagotchiapp

fun checkStatus(petStats: PetStatus){

    when(petStats.hunger){

        1 ->{
            //not hungry
        }
        2 ->{
            //hungry
        }
        3 ->{
            //very hungry
        }
    }

    when(petStats.dirty){

        1 ->{
            //not dirty
        }
        2 ->{
            //dirty
        }
        3 ->{
            //very dirty
        }
    }

}