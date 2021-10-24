package com.mehyo.darbuka.di

import android.app.Application
import androidx.room.Room
import com.mehyo.darbuka.util.Constants
import com.mehyo.darbuka.database.SquareReposDB
import com.mehyo.darbuka.database.repo.DatabaseRepository
import com.mehyo.darbuka.database.repo.DatabaseRepositoryImp
import com.mehyo.darbuka.main_repository.MainRepository
import com.mehyo.darbuka.main_repository.MainRepositoryImp
import com.mehyo.darbuka.network.APIRequests
import com.mehyo.darbuka.network.repo.NetworkRepository
import com.mehyo.darbuka.network.repo.NetworkRepositoryImp
import com.mehyo.darbuka.ui.viewmodels.MyViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel { MyViewModel(repository = get()) }
}

val networkModule = module {
    //creating network api using retrofit variable
    fun createNetworkApi(retrofit: Retrofit) = retrofit.create(APIRequests::class.java)

    //Building retrofit variable
    fun retrofitBuilder() = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .build()

    single { createNetworkApi(retrofit = get()) }
    single { retrofitBuilder() }
}

val databaseModule = module {
    fun getDatabaseInstance(application: Application): SquareReposDB = 
        Room.databaseBuilder(
        application,
        SquareReposDB::class.java,
        Constants.DATABASE_NAME
        ).build()
    
    single { getDatabaseInstance(androidApplication()) }
}

val useCaseModule = module {}

val repositoryModule = module {
    single<DatabaseRepository> { DatabaseRepositoryImp(db=get()) }
    single<NetworkRepository> { NetworkRepositoryImp(api=get()) }
    single<MainRepository> { MainRepositoryImp(networkRepository=get(),databaseRepository=get()) }
}