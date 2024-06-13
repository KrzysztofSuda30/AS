package com.amw.fragmenty
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amw.fragmenty.databinding.FragmentDrugiBinding

class Drugi : Fragment() {

    private var _binding: FragmentDrugiBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDrugiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->
            binding.nazwa.text = bundle.getString("nazwa")
            binding.stary.text = bundle.getString("stary")
            binding.nowy.text = bundle.getString("nowy")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
