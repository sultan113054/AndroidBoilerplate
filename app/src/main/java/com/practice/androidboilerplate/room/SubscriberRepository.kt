package com.practice.androidboilerplate.room

class SubscriberRepository(private val subscriberDao: SubscriberDao) {
    val getAllSubscriber=subscriberDao.getAllSubscriber()

    suspend fun insertSubscriber(subscriber: Subscriber):Long{
      return  subscriberDao.insertSubscriber(subscriber)
    }

    suspend fun updateSubscriber(subscriber: Subscriber):Int{
      return  subscriberDao.updateSubscriber(subscriber)
    }
    suspend fun deleteSubscriber(subscriber: Subscriber):Int{
      return  subscriberDao.deleteSubscriber(subscriber)
    }
    suspend fun deleteAllSubscriber():Int{
      return  subscriberDao.deleteAll()
    }
}