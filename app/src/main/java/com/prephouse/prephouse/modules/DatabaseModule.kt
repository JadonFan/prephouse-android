package com.prephouse.prephouse.modules

import android.content.Context
import androidx.room.Room
import com.prephouse.prephouse.databases.PrephouseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private const val PREPHOUSE_DB = "prephouse_db"

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, PrephouseDatabase::class.java, PREPHOUSE_DB).build()

    @Singleton
    @Provides
    fun providesMediaDownloadDao(db: PrephouseDatabase) = db.mediaDownloadDao()

    @Singleton
    @Provides
    fun providesFeedbackDao(db: PrephouseDatabase) = db.feedbackDao()
}
