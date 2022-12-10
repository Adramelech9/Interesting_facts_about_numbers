package com.internship_test.ifan.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.internship_test.ifan.model.entity.FactEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert
    fun insertFact(fact: FactEntity)
    @Query("SELECT * FROM facts")
    fun getAllFact(): Flow<List<FactEntity>>
}