package com.binar.latihan_ui_design

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.binar.latihan_ui_design.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
        setOnClickListener()
    }

    private fun setOnClickListener() {
        var isEdit = true
        binding.btnEdit.setOnClickListener {
            if (isEdit) {
                editData(isEdit)
                isEdit = false
            } else {
                editData(isEdit)
                isEdit = true
            }
        }
        binding.btnSave.setOnClickListener {
            if (validateInput()) {
                saveData()
            }
        }
    }

    private fun saveData() {
        val name = binding.etName.text.toString()
        val country = binding.etCountry.text.toString()

        binding.etName.apply {
            setText(name)
            isEnabled = false
        }
        binding.etCountry.apply {
            setText(country)
            isEnabled = false
        }
        val intent = Intent()
        intent.putExtra(MainActivity.NAME, name)
        intent.putExtra(MainActivity.COUNTRY, country)
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun editData(isEdit: Boolean) {
        if (isEdit) {
            binding.etName.isEnabled = true
            binding.etCountry.isEnabled = true
        } else {
            binding.etName.isEnabled = false
            binding.etCountry.isEnabled = false
        }
    }

    private fun getData() {
        val name = intent.getStringExtra(MainActivity.NAME)
        val country = intent.getStringExtra(MainActivity.COUNTRY)
        Snackbar.make(binding.root, getString(R.string.data_sent_successfully), Snackbar.LENGTH_LONG).show()

        binding.etName.apply {
            setText(name)
            isEnabled = false
        }
        binding.etCountry.apply {
            setText(country)
            isEnabled = false
        }
    }

    private fun validateInput(): Boolean {
        val name = binding.etName.text.toString()
        val country = binding.etCountry.text.toString()
        var isValid = true

        if (name.isEmpty()) {
            binding.etName.error = "Fill your name"
            isValid = false
        } else if (country.isEmpty()) {
            binding.etCountry.error = "Fill your country"
            isValid = false
        }
        return isValid
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}