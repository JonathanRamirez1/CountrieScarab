package com.rj2techsolutions.countriescarab.di

import android.content.Context
import androidx.room.Room
import com.rj2techsolutions.countriescarab.data.local.database.CountryDatabase
import com.rj2techsolutions.countriescarab.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, CountryDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideAppDatabase(appDatabase: CountryDatabase) = appDatabase.countryDao()
}