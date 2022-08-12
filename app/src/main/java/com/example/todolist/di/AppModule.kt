package com.example.todolist.di

import android.app.Application
import androidx.room.Room
import com.example.todolist.data.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application, callback: TodoDatabase.Callback) =
        Room.databaseBuilder(app, TodoDatabase::class.java, "todo_database")
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()


    @Provides
    fun provideTodoDao(database: TodoDatabase) = database.todoDao()

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())
}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope