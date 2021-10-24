package com.mehyo.darbuka.model

data class SquareModelItem(
    val id: Int,
    val name: String,
    val stargazers_count: Int,
)

fun SquareModelItem.toSquareRepos():SquareRepos{
    return SquareRepos(
        id = id,
        name= name,
        stargazers_count= stargazers_count,
        isBookmarked = false
    )
}