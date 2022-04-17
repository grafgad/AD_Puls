package com.example.adpuls

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.adpuls.databinding.FragmentAdValueBinding


class AdValueFragment : Fragment() {

    private lateinit var viewModel: AdViewModel
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
        viewModel = ViewModelProvider(requireActivity())[AdViewModel::class.java]
        viewModel.pulse.observe(viewLifecycleOwner, ::pulseValue)
        viewModel.bloodPressure.observe(viewLifecycleOwner, ::pressureValue)

        binding.sendButton.setOnClickListener {
            viewModel.onSendButtonClicked()
            requireActivity().supportFragmentManager.beginTransaction()
                .hide(this)
                .add(R.id.container, MainFragment())
                .commit()
        }
    }

    private fun pulseValue(string: String) {
        binding.pulseInput.setText(string)
    }

    private fun pressureValue(string: String) {
        binding.bloodPressureInput.setText(string)
    }

    companion object {
        fun newInstance() = AdValueFragment()
    }
}
