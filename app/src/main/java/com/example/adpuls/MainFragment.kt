package com.example.adpuls

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adpuls.databinding.FragmentMainBinding
import com.google.firebase.firestore.FirebaseFirestore


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()
    private lateinit var recyclerView: RecyclerView
    private val adapter = ValueAdapter()
    private val valuesList = ValueList.valueList

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        recyclerView = binding.recycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db.collection("ad_puls_collection")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result) {
                        Log.d("tag", document.id + " => " + document.data)
                        valuesList.add(
                            Value(
                                document.id,
                                document.data["time"].toString(),
                                document.data["pressure"].toString(),
                                document.data["pulse"].toString()
                            )
                        )
                        adapter.notifyItemInserted(valuesList.lastIndex)
                    }
                } else {
                    Log.e("tag", "Error getting data", task.exception)
                }
                adapter.setData(valuesList)
            }

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