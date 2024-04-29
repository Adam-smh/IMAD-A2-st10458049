package com.example.tamagotchiapp

    fun feedPet(petStatus: PetStatus): PetStatus {

        if (petStatus.hunger != 1){
            petStatus.hunger--
            petStatus.dirty++
        }

        return petStatus
    }

    fun cleanPet(petStatus: PetStatus): PetStatus {

        if(petStatus.dirty != 1){
            petStatus.dirty = 1
            petStatus.boredom++
        }


        return petStatus
    }

    fun playPet(petStatus: PetStatus): PetStatus {

        if (petStatus.boredom != 1){
            petStatus.boredom - 1
            petStatus.dirty + 1
            petStatus.hunger + 1
        }

        return petStatus
    }
