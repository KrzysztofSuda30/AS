package com.amw.projekt

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.amw.projekt.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicjalizacja Spinnera
        val spinner = binding.wybr
        ArrayAdapter.createFromResource(
            this,
            R.array.spinner_items,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // Obsługa wyboru z Spinnera
                val selectedItem = parent.getItemAtPosition(position).toString()
                binding.przejdz.setOnClickListener {
                    when (selectedItem) {
                        "Skwer Kościuszki" -> openSkwerKosciuszki()
                        "Akwarium Gdyńskie" -> openAkwarium()
                        "Muzeum Emigracji" -> openMuzeum()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }

    private fun openSkwerKosciuszki() {
        val intent = Intent(this, Skwer::class.java)
        startActivity(intent)
    }

    private fun openAkwarium() {
        val intent = Intent(this, Akwarium::class.java)
        startActivity(intent)
    }

    private fun openMuzeum() {
        val intent = Intent(this, Muzeum::class.java)
        startActivity(intent)
    }
}