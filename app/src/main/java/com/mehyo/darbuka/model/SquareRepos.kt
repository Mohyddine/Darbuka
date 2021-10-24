package com.mehyo.darbuka.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "square_repos")
data class SquareRepos(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val stargazers_count: Int,
    //true=1 and false=0
    val isBookmarked:Boolean
)

fun SquareRepos.toSquareModelItem():SquareModelItem{
    return SquareModelItem(
        id = id,
        name= name,
        stargazers_count= stargazers_count
    )
}