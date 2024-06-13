package com.amw.fragmenty
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.amw.fragmenty.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Pierwszy.PierwszyListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Wyświetlamy PierwszyFragment jako domyślny
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(binding.fragmentContainerView3.id, Pierwszy())
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
    override fun doFragmentu2(tekst: String, isChecked: Boolean) {
        // Przetwarzamy dane i przekazujemy do DrugiFragment
        val numbers = if (tekst.isEmpty()) {
            emptyList()
        } else {
            tekst.split(",").map { it.trim().toIntOrNull() }.filterNotNull()
        }

        val sortedNumbers = if (isChecked) {
            quickSort(numbers.toMutableList())
        } else {
            bubbleSort(numbers.toMutableList())
        }

        val bundle = Bundle().apply {
            putString("nazwa", "Metoda sortowania: ${if (isChecked) "Szybkie" else "Bąbelkowe"}")
            putString("stary", "Początkowy ciąg liczb: $tekst")
            putString("nowy", "Posortowany ciąg liczb: ${sortedNumbers.joinToString(", ")}")
        }

        val drugiFragment = Drugi().apply {
            arguments = bundle
        }

        supportFragmentManager.commit {
            replace(binding.fragmentContainerView14.id, drugiFragment)
            addToBackStack(null)  // Opcjonalnie dodajemy do back stack, aby móc wrócić do poprzedniego fragmentu
        }
    }
}
