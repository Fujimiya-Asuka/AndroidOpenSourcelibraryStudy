package com.asuka.androidopensourcelibrarystudydemo.view.fragerment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.asuka.androidopensourcelibrarystudydemo.R
import com.asuka.androidopensourcelibrarystudydemo.databinding.FragmentBBinding


class FragmentB : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var _binding: FragmentBBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(
                "key"
            )
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBBinding.inflate(inflater, container, false)
        binding.textView.text = param1
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.toCBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.fragmentC)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}