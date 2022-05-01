package com.example.adpuls.ui.advalue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.adpuls.MainFragment
import com.example.adpuls.R
import com.example.adpuls.databinding.FragmentAdValueBinding

class AdValueFragment : Fragment() {

    private val viewModel: AdViewModel by viewModels()
    private var _binding: FragmentAdValueBinding? = null
    private val binding get() = _binding!!

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
            val pulse = binding.pulseInput.text.toString()
            val bloodPressure = binding.bloodPressureInput.text.toString()
            viewModel.onSendButtonClicked(pulse, bloodPressure)
            backToList()
        }
    }

    private fun backToList() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment.newInstance())
            .commit()
    }

    companion object {
        fun newInstance() = AdValueFragment()
    }
}
