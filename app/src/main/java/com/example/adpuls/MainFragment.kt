package com.example.adpuls

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.adpuls.databinding.FragmentMainBinding
import com.google.firebase.firestore.FirebaseFirestore


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()
    private var text = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
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
                        text += document.data.toString()+ "\n"
                        binding.textField.text = text
                    }
                } else {
                    Log.e("tag", "Error getting data", task.exception)
                }
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