package com.amw.fragmenty
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amw.fragmenty.databinding.FragmentPierwszyBinding

class Pierwszy : Fragment() {

    private var _binding: FragmentPierwszyBinding? = null
    private val binding get() = _binding!!

    var pierwszyFragmentCallback: PierwszyListener? = null

    interface PierwszyListener {
        fun doFragmentu2(tekst: String, isChecked: Boolean)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            pierwszyFragmentCallback = context as PierwszyListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement PierwszyListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPierwszyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sortuj.setOnClickListener {
            val tekst = binding.ciog.text.toString()
            val isChecked = binding.przel.isChecked
            pierwszyFragmentCallback?.doFragmentu2(tekst, isChecked)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
