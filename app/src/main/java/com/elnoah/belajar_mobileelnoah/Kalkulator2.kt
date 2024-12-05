package com.elnoah.belajar_mobileelnoah

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Kalkulator2 : AppCompatActivity() {
    // Deklarasi variabel
    private lateinit var etDisplay: TextView
    private lateinit var btBersihkan: Button
    private lateinit var btPangkat: Button
    private lateinit var btNegatif: Button
    private lateinit var btBagi: Button
    private lateinit var btKali: Button
    private lateinit var btKurang: Button
    private lateinit var btTambah: Button
    private lateinit var btPersen: Button
    private lateinit var btSama: Button
    private lateinit var btKoma: Button

    private val angkaButtons = mutableMapOf<Button, String>()
    private var nilaiAwal: Double = 0.0
    private var aksi: String = ""
    private var koma: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_kalkulator2)

        init() // Inisialisasi view
        setupListeners() // Atur tombol
    }

    // Inisialisasi semua komponen UI
    private fun init() {
        etDisplay = findViewById(R.id.etDisplay)
        btBersihkan = findViewById(R.id.btbersihkan)
        btPangkat = findViewById(R.id.btpangkat)
        btNegatif = findViewById(R.id.btnegatif)
        btBagi = findViewById(R.id.btbagi)
        btKali = findViewById(R.id.btkali)
        btKurang = findViewById(R.id.btkurang)
        btTambah = findViewById(R.id.bttambah)
        btPersen = findViewById(R.id.btpersen)
        btSama = findViewById(R.id.btsama)
        btKoma = findViewById(R.id.btkoma)

        // Inisialisasi tombol angka
        angkaButtons[findViewById(R.id.btangka0)] = "0"
        angkaButtons[findViewById(R.id.btangka1)] = "1"
        angkaButtons[findViewById(R.id.btangka2)] = "2"
        angkaButtons[findViewById(R.id.btangka3)] = "3"
        angkaButtons[findViewById(R.id.btangka4)] = "4"
        angkaButtons[findViewById(R.id.btangka5)] = "5"
        angkaButtons[findViewById(R.id.btangka6)] = "6"
        angkaButtons[findViewById(R.id.btangka7)] = "7"
        angkaButtons[findViewById(R.id.btangka8)] = "8"
        angkaButtons[findViewById(R.id.btangka9)] = "9"

        etDisplay.text = ""
    }

    // Atur tombol untuk event listener
    private fun setupListeners() {
        // Tombol bersihkan
        btBersihkan.setOnClickListener {
            koma = false
            aksi = ""
            etDisplay.text = ""
        }

        // Tombol negatif
        btNegatif.setOnClickListener {
            if (etDisplay.text.isNotEmpty()) {
                val current = etDisplay.text.toString().toDouble()
                etDisplay.text = (-current).toString()
            }
        }

        // Tombol angka
        angkaButtons.forEach { (button, value) ->
            button.setOnClickListener {
                etDisplay.append(value)
            }
        }

        // Tombol koma
        btKoma.setOnClickListener {
            if (!etDisplay.text.contains(".")) {
                etDisplay.append(".")
            }
        }

        // Tombol operasi
        btTambah.setOnClickListener { setOperator("tambah") }
        btKurang.setOnClickListener { setOperator("kurang") }
        btKali.setOnClickListener { setOperator("kali") }
        btBagi.setOnClickListener { setOperator("bagi") }
        btPangkat.setOnClickListener { setOperator("pangkat") }

        // Tombol persen
        btPersen.setOnClickListener {
            if (etDisplay.text.isNotEmpty()) {
                val nilaiBaru = etDisplay.text.toString().toDouble()
                val hasil = calculatePercentage(nilaiBaru)
                etDisplay.text = hasil.toString()
            }
        }

        // Tombol hasil
        btSama.setOnClickListener {
            if (aksi.isNotEmpty() && etDisplay.text.isNotEmpty()) {
                val nilaiBaru = etDisplay.text.toString().toDouble()
                val hasil = calculateResult(nilaiBaru)
                etDisplay.text = DecimalFormat("#.###").format(hasil)
                aksi = ""
            }
            btPangkat.setOnClickListener {
                if (etDisplay.text.isNotEmpty()) {
                    setOperator("pangkat")
                } else {
                    etDisplay.text = "Masukkan angka"
                }
            }

        }
    }

    // Set operator matematika
    private fun setOperator(operator: String) {
        if (etDisplay.text.isNotEmpty()) {
            nilaiAwal = etDisplay.text.toString().toDouble()
            etDisplay.text = ""
            aksi = operator
        }
    }

    // Hitung hasil berdasarkan operator
    private fun calculateResult(nilaiBaru: Double): Double {
        return when (aksi) {
            "tambah" -> nilaiAwal + nilaiBaru
            "kurang" -> nilaiAwal - nilaiBaru
            "kali" -> nilaiAwal * nilaiBaru
            "bagi" -> nilaiAwal / nilaiBaru
            "pangkat" -> Math.pow(nilaiAwal, nilaiBaru)
            else -> 0.0
        }
    }

    // Hitung nilai persen
    private fun calculatePercentage(nilaiBaru: Double): Double {
        return if (aksi.isNotEmpty()) {
            when (aksi) {
                "tambah" -> nilaiAwal + (nilaiAwal * nilaiBaru / 100)
                "kurang" -> nilaiAwal - (nilaiAwal * nilaiBaru / 100)
                "kali" -> nilaiAwal * (nilaiBaru / 100)
                "bagi" -> nilaiAwal / (nilaiBaru / 100)
                else -> 0.0
            }
        } else {
            nilaiBaru / 100
        }
    }
}
