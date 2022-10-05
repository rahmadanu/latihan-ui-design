package com.binar.latihan_ui_design

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat.startActivityForResult
import com.binar.latihan_ui_design.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                getData(it.data?.getStringExtra(NAME), it.data?.getStringExtra(COUNTRY))
            }
        }

    private fun getData(stringExtra: String?, stringExtra1: String?) {
        binding.etName.setText(stringExtra)
        binding.etCountry.setText(stringExtra1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSend.setOnClickListener {
            val name = binding.etName.text.toString()
            val country = binding.etCountry.text.toString()

            if (validateInput()) {
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra(NAME, name)
                intent.putExtra(COUNTRY, country)
                resultLauncher.launch(intent)
            }
        }
    }

    private fun validateInput(): Boolean {
        val name = binding.etName.text.toString()
        val country = binding.etCountry.text.toString()
        var isValid = true

        if (name.isEmpty()) {
            binding.etName.error = getString(R.string.fill_your_name)
            isValid = false
        } else if (country.isEmpty()) {
            binding.etCountry.error = getString(R.string.fill_your_country)
            isValid = false
        }
        return isValid
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val NAME = "name"
        const val COUNTRY = "country"
    }
}