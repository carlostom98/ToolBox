package com.poc.postitapp.data

import com.poc.postitapp.domain.entities.SuperHeroData

class SuperHeroesDataBase: DataFromRemote {

    private val listOfSuperHeroes = listOf(
        SuperHeroData("super 1", "5"),
        SuperHeroData("super 2", "6"),
        SuperHeroData("super 3", "7"),
        SuperHeroData("super 4", "8"),
        SuperHeroData("super 5", "9"),
        SuperHeroData("super 6", "10"),
        SuperHeroData("super 7", "11"),
        SuperHeroData("super 8", "12"),
        SuperHeroData("super 9", "13"),
        SuperHeroData("super 10", "14"),
        SuperHeroData("super 11", "15"),
        SuperHeroData("super 12", "16"),
        SuperHeroData("super 13", "17"),
        SuperHeroData("super 14", "18"),
        SuperHeroData("super 15", "19"),
        SuperHeroData("super 16", "20"),
        SuperHeroData("super 16", "22"),
        SuperHeroData("super 16", "24"),
        SuperHeroData("super 16", "25"),
    )

    override fun create(): List<SuperHeroData> {
        return listOfSuperHeroes
    }

}