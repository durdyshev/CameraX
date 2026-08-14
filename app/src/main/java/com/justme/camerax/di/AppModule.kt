package com.justme.camerax.di

import android.app.Application
import androidx.room.Room
import com.justme.camerax.data.local.db.SaleDatabase
import com.justme.camerax.data.repository.Repository
import com.justme.camerax.data.repository.RepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideMyDataBase(app: Application): SaleDatabase {
        return Room.databaseBuilder(
            app,
            SaleDatabase::class.java,
            "MyDataBase"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMyRepository(saleDb: SaleDatabase): RepositoryInterface {
        return Repository(saleDb.dao)
    }
}