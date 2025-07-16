package com.example.peyademoapp.data.remote.api.di

import com.example.peyademoapp.data.repository.users.UsersDataSource
import com.example.peyademoapp.data.repository.users.UsersDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UsersModule {

    @Binds
    @Singleton
    abstract fun bindUsersDataSource(
        impl: UsersDataSourceImpl
    ): UsersDataSource
}
