package com.example.tamagotchiapp

    fun outOfRangeStats(petStatus: PetStatus): PetStatus {
        if (petStatus.hunger > 3){
            petStatus.hunger = 3
        }
        if (petStatus.boredom > 3){
            petStatus.boredom = 3
        }
        if (petStatus.dirty > 3){
            petStatus.dirty = 3
        }

        return petStatus
    }
    fun feedPet(petStatus: PetStatus): PetStatus {

        outOfRangeStats(petStatus)
        if (petStatus.hunger != 1){
            petStatus.hunger--
            petStatus.dirty++
        }

        return petStatus
    }

    fun cleanPet(petStatus: PetStatus): PetStatus {

        outOfRangeStats(petStatus)
        if(petStatus.dirty != 1){
            petStatus.dirty = 1
        }
        return petStatus
    }

    fun playPet(petStatus: PetStatus): PetStatus {

        outOfRangeStats(petStatus)
        if (petStatus.boredom != 1){
            petStatus.boredom--
            petStatus.dirty++
            petStatus.hunger++
        }

        return petStatus
    }
