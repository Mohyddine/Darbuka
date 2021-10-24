package com.mehyo.darbuka.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mehyo.darbuka.model.SquareRepos

@Database(entities = [SquareRepos::class],version = 1)
abstract class SquareReposDB: RoomDatabase() {
    abstract fun squareReposDAO(): SquareReposDAO
}