package com.amw.aktywnosc

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.amw.aktywnosc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.sortuj.setOnClickListener {
            val ciag = binding.ciog.text.toString()
            if (ciag.isEmpty() || ciag == getString(R.string.wpisz_ci_g)) {
                Toast.makeText(this, "Wpisz ciąg liczb!", Toast.LENGTH_SHORT).show()
            } else {
                val liczby = ciag.split(",").map { it.trim().toInt() }
                val posortowanyCiag = if (binding.przel.isChecked) {
                    quickSort(liczby.toMutableList())
                } else {
                    bubbleSort(liczby.toMutableList())
                }
                val metoda = if (binding.przel.isChecked) "Szybkie" else "Bąbelkowe"

                val intent = Intent(this, Wynik::class.java).apply {
                    putExtra("metoda", metoda)
                    putExtra("stary", ciag)
                    putExtra("nowy", posortowanyCiag.joinToString(", "))
                }
                startActivity(intent)
            }
        }
    }

    private fun bubbleSort(lista: MutableList<Int>): List<Int> {
        val n = lista.size
        for (i in 0 until n - 1) {
            for (j in 0 until n - i - 1) {
                if (lista[j] > lista[j + 1]) {
                    val temp = lista[j]
                    lista[j] = lista[j + 1]
                    lista[j + 1] = temp
                }
            }
        }
        return lista
    }

    private fun quickSort(lista: MutableList<Int>): List<Int> {
        if (lista.size <= 1) return lista
        val pivot = lista[lista.size / 2]
        val equal = lista.filter { it == pivot }
        val less = lista.filter { it < pivot }
        val greater = lista.filter { it > pivot }
        return quickSort(less.toMutableList()) + equal + quickSort(greater.toMutableList())
    }
}
