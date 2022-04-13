package com.example.adpuls

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.adpuls.databinding.FragmentAdValueBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*


class AdValueFragment : Fragment() {

    private lateinit var viewModel: AdViewModel
    private var _binding: FragmentAdValueBinding? = null
    private val binding get() = _binding!!

    private val db = FirebaseFirestore.getInstance()
    private var adpuls = mutableMapOf("time" to "", "pressure" to "", "pulse" to "")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdValueBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sendButton.setOnClickListener {
            getCurrentDate()
            pulseValue()
            pressureValue()

            db.collection("ad_puls_collection")
                .add(adpuls)
                .addOnSuccessListener {
                    Log.d("tag", "it happened!")
                }
                .addOnFailureListener {
                        Log.d("tag", "doesn't work((")
                }

            requireActivity().supportFragmentManager.beginTransaction()
                .hide(this)
                .add(R.id.container, MainFragment())
                .commit()
        }

    }

    private fun getCurrentDate() {
        val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm")
        adpuls["time"] = sdf.format(Date())
    }

    private fun pulseValue() {
        adpuls["pulse"] = binding.pulseInput.text.toString()
    }

    private fun pressureValue() {
        adpuls["pressure"] = binding.bloodPressureInput.text.toString()
    }


    companion object {
        fun newInstance() = AdValueFragment()
    }
}
