package com.practice.androidboilerplate.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SubscriberDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubscriber(subscriber: Subscriber):Long

    @Update
    suspend fun updateSubscriber(subscriber: Subscriber):Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubscribers(subscribers: List<Subscriber>)

    @Delete
    suspend fun deleteSubscriber(subscriber: Subscriber):Int

    @Query("Delete from subscriber_data_table")
    suspend fun deleteAll():Int


    @Query("Select * from subscriber_data_table")
    fun getAllSubscriber():LiveData<List<Subscriber>>

}