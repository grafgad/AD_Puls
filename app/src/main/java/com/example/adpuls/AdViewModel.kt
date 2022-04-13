package com.example.adpuls

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.DateFormat.getDateTimeInstance
import java.text.SimpleDateFormat
import java.util.*

class AdViewModel : ViewModel() {

    private val repository = ValueList.valueList
    val bloodPressure = MutableLiveData<String>()
    val pulse = MutableLiveData<String>()



    private fun getCurrentDate():String{
        val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm")
        return sdf.format(Date())
    }

    fun onSendButtonClicked(pulse: MutableLiveData<String>, bloodPressure: MutableLiveData<String>){

        repository.add(Value(repository.size+1, getCurrentDate(), bloodPressure.value.toString(), pulse.value.toString()))
    }






}

