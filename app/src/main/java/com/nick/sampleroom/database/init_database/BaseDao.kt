package com.nick.sampleroom.database.init_database

import androidx.room.*

interface BaseDao<O, L> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplaceObject(data: O)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplaceNullableObject(data: O?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplaceObject(vararg data: O)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplaceList(data: L)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAndIgnoreReplaceObject(data: O)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAndIgnoreReplaceNullableObject(data: O?)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAndIgnoreReplaceObject(vararg data: O)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAndIgnoreReplaceList(data: L)

    @Insert
    suspend fun insertNullableObject(data: O?)

    @Insert
    suspend fun insertObject(data: O)

    @Insert
    suspend fun insertObject(vararg data: O)

    @Insert
    suspend fun insertList(data: L)

    @Update
    suspend fun updateObject(data: O)

    @Delete
    suspend fun deleteObject(data: O)

    @Delete
    suspend fun deleteList(data: L)
}