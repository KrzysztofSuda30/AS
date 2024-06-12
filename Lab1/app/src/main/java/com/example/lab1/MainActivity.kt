package com.example.lab1

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val buttonSortuj: Button = findViewById(R.id.Sortuj)
        val textViewNazwa: TextView = findViewById(R.id.Nazwa)
        val switchPrzel: Switch = findViewById(R.id.Przel)
        val editTextCiog: EditText = findViewById(R.id.ciog)
        val textViewstary: TextView = findViewById(R.id.stary)
        val textViewnowy: TextView = findViewById(R.id.nowy)

        buttonSortuj.setOnClickListener {
            val ciag: String = editTextCiog.text.toString()
            if (ciag.isEmpty()) {
                textViewNazwa.text = "Wpisz ciąg liczb!"
            }
            if (ciag.toString()=="Miejsce na ciąg liczb") {
                textViewNazwa.text = "Wpisz ciąg liczb!"
            }else {
                val liczby: List<Int> = ciag.split(",").map { it.trim().toInt() }
                if (switchPrzel.isChecked) {
                    val posortowanyCiag = quickSort(liczby.toMutableList())
                    textViewNazwa.text = "Metoda sortowania: Szybkie"
                    textViewstary.text = "Początkowy ciąg liczb: $ciag"
                    textViewnowy.text =
                        "Posortowany ciąg liczb: ${posortowanyCiag.joinToString(", ")}"
                } else {
                    val posortowanyCiag = bubbleSort(liczby.toMutableList())
                    textViewNazwa.text = "Metoda sortowania: Bąbelkowe"
                    textViewstary.text = "Początkowy ciąg liczb: $ciag"
                    textViewnowy.text =
                        "Posortowany ciąg liczb: ${posortowanyCiag.joinToString(", ")}"
                }
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
