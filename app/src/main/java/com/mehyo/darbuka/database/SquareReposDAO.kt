package com.mehyo.darbuka.database

import androidx.room.*
import com.mehyo.darbuka.model.SquareRepos
import kotlinx.coroutines.flow.Flow

@Dao
interface SquareReposDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRepo(item:SquareRepos)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRepos(items:List<SquareRepos>)

    @Delete
    suspend fun deleteRepo(item:SquareRepos)

    @Update
    suspend fun updateRepo(item:SquareRepos)

    @Query("delete from square_repos")
    suspend fun deleteALLRepos()

    @Query("delete from square_repos where isBookmarked = 1")
    suspend fun deleteALLBookmarkedRepos()

    @Query("select * from square_repos where id = :ID")
    suspend fun getRepoByID(ID:Int):SquareRepos

    @Query("select * from square_repos where isBookmarked = 1")
    fun getBookmarkedRepos():Flow<List<SquareRepos>>

    @Query("select * from square_repos")
    fun getRepos():Flow<List<SquareRepos>>
}