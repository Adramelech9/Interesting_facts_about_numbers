package com.internship_test.ifan.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.internship_test.ifan.model.dao.Dao
import com.internship_test.ifan.model.entity.FactEntity

@Database(entities = [FactEntity::class], version = 1)
abstract class Fact : RoomDatabase() {
    abstract fun getDao(): Dao

    companion object {
        fun getData(context: Context): Fact = Room.databaseBuilder(
            context.applicationContext,
            Fact::class.java,
            "facts.db"
        ).build()
    }
}