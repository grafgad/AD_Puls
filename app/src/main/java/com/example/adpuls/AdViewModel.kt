package com.example.adpuls

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class AdViewModel : ViewModel() {

    val bloodPressure = MutableLiveData<String>()
    val pulse = MutableLiveData<String>()
    private val db = FirebaseFirestore.getInstance()
    private var adpuls = mutableMapOf("time" to "", "pressure" to "", "pulse" to "")

    fun onSendButtonClicked() {
        getPulse()
        getCurrentDate()
        getPressure()

        db.collection("ad_puls_collection")
            .add(adpuls)
            .addOnSuccessListener {
                Log.d("tag", "it happened!")
                println(adpuls)
            }
            .addOnFailureListener {
                Log.d("tag", "doesn't work((")
            }
    }

    private fun getPressure() {
        adpuls["pressure"] = bloodPressure.toString()
    }

    @SuppressLint("SimpleDateFormat")
    private fun getCurrentDate() {
        val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm")
        adpuls["time"] = sdf.format(Date())
    }

    private fun getPulse() {
        adpuls["pulse"] = pulse.toString()
    }


}

