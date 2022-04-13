package com.example.adpuls

import android.os.Bundle
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

    private var pulse = ""
    private var pressure = ""
    private val repository = ValueList.valueList


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdValueBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel = ViewModelProvider(requireActivity())[AdViewModel::class.java]
//          viewModel.bloodPressure.observe(viewLifecycleOwner, ::pressureValue)
//        viewModel.pulse.observe(viewLifecycleOwner, ::pulseValue)

        binding.sendButton.setOnClickListener {
//            viewModel.onSendButtonClicked()
            repository.add(
                Value(
                    repository.size + 1,
                    getCurrentDate(),
                    pressureValue(),
                    pulseValue()
                )
            )
            requireActivity().supportFragmentManager.beginTransaction()
                .hide(this)
                .add(R.id.container, MainFragment())
                .commit()
        }

    }

    private fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm")
        return sdf.format(Date())
    }

    private fun pulseValue(): String {
        pulse = binding.pulseInput.text.toString()
        return pulse
    }

    private fun pressureValue(): String {
        pressure = binding.bloodPressureInput.text.toString()
        return pressure
    }


    companion object {
        fun newInstance() = AdValueFragment()
    }
}