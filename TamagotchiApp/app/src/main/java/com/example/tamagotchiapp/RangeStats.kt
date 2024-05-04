package com.example.tamagotchiapp

//outOfRangeStats ensures that pet stats stays within the 1-3 range
fun outOfRangeStats(stat: Int): Int {

    var res: Int

    when{
        stat > 300 -> {
            res = 300
        }
        stat < 0 ->{
            res = 0
        }
        else -> {
            res = stat
        }
    }
    return res
}