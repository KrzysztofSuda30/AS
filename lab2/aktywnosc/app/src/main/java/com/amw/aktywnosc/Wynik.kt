package com.amw.aktywnosc

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.amw.aktywnosc.databinding.ActivityWynikBinding

class Wynik : AppCompatActivity() {
    private lateinit var binding: ActivityWynikBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWynikBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val metoda = intent.getStringExtra("metoda")
        val stary = intent.getStringExtra("stary")
        val nowy = intent.getStringExtra("nowy")

        binding.nazwa.text = "Metoda sortowania: $metoda"
        binding.stary.text = "Początkowy ciąg liczb: $stary"
        binding.nowy.text = "Posortowany ciąg liczb: $nowy"

        binding.pow.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
