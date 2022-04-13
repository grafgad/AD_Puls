package com.example.adpuls

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.adpuls.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var recycler: RecyclerView
    private val adapter = ValueAdapter()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        recycler = binding.recycler
        recycler.adapter = adapter
        adapter.setData(ValueList.valueList)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fab.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .hide(this)
                .addToBackStack(null)
                .add(R.id.container, AdValueFragment.newInstance())
                .commit()
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}