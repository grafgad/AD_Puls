package com.example.adpuls.ui.advalue

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class AdViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private var adpuls = mutableMapOf("time" to "", "pressure" to "", "pulse" to "")

    fun onSendButtonClicked(pulse: String, bloodPressure: String) {
        fillAdPulse(pulse, bloodPressure)

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

    private fun fillAdPulse(pulse: String, bloodPressure: String) {
        adpuls["pulse"] = pulse
        adpuls["pressure"] = bloodPressure
        val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
        adpuls["time"] = sdf.format(Date())
    }
}

