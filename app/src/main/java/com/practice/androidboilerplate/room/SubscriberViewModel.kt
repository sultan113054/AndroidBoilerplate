package com.practice.androidboilerplate.room

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SubscriberViewModel(private val repository: SubscriberRepository) : ViewModel() {

    private var isUpdate = false
    private lateinit var subscriber: Subscriber
    val subscribers = repository.getAllSubscriber
    val name = MutableLiveData<String?>()
    val email = MutableLiveData<String?>()

    private var statusMessage = MutableLiveData<Event<String>>()
    val status: LiveData<Event<String>> get() = statusMessage


    val saveOrUpdateButtontext = MutableLiveData<String>()
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    init {
        saveOrUpdateButtontext.value = "Save"
        clearAllOrDeleteButtonText.value = "clear All"

    }

    fun saveOrUpdate() {

        if (name.value == null) {
            statusMessage.value = Event("Please enter name")

        } else if (email.value == null) {
            statusMessage.value = Event("Please enter email")

        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.value!!).matches()) {
            statusMessage.value = Event("InValid email")

        } else {
            if (isUpdate) {
                subscriber.name = name.value!!
                subscriber.email = email.value!!
                update(subscriber)
                name.value = null
                email.value = null
                saveOrUpdateButtontext.value = "Save"
                clearAllOrDeleteButtonText.value = "ClearAll"
                isUpdate = false
            } else {
                val name = name.value!!
                val email = email.value!!
                insert(Subscriber(name, 0, email))
                this.name.value = null
                this.email.value = null
            }
        }
    }

    fun initUpdateOrSave(subscriber: Subscriber) {
        name.value = subscriber.name
        email.value = subscriber.email
        saveOrUpdateButtontext.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"
        isUpdate = true
        this.subscriber = subscriber

    }

    fun clearAllOrDelete() {
        if (isUpdate) {
            delete(subscriber)
        } else {
            deleteAll()
        }
    }

    fun insert(subscriber: Subscriber) =
        viewModelScope.launch {
            val id = repository.insertSubscriber(subscriber)
            if (id > -1)
                statusMessage.value = Event(" Subscriber inserted successfully id: ${id} ")
            else
                statusMessage.value = Event(" Error Occured")
        }

    fun update(subscriber: Subscriber) =
        viewModelScope.launch {
            var noOfRow = repository.updateSubscriber(subscriber)
            if (noOfRow > 0)
                statusMessage.value = Event("$noOfRow Subscriber updated successfully")
            else statusMessage.value = Event(" Error occured")

        }

    fun delete(subscriber: Subscriber) =
        viewModelScope.launch {
            var nowOfRow = repository.deleteSubscriber(subscriber)
            name.value = null
            email.value = null
            saveOrUpdateButtontext.value = "Save"
            clearAllOrDeleteButtonText.value = "ClearAll"
            isUpdate = false
            if (nowOfRow > 0)
                statusMessage.value = Event("$nowOfRow Subscriber deleted successfully")
            else statusMessage.value = Event(" Error occured")
        }

    fun deleteAll() =
        viewModelScope.launch {
            var noOfRow = repository.deleteAllSubscriber()
            if (noOfRow > 0)
                statusMessage.value = Event("$noOfRow All Subscriber deleted successfully")
            else statusMessage.value = Event(" Error occured")

        }

}