package com.elnoah.belajar_mobileelnoah

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomePage : AppCompatActivity() {
    lateinit var cvform: CardView
    lateinit var cvcalculator: CardView
    lateinit var cvkonversi: CardView
    lateinit var cvprofile: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        // Mengatur padding untuk system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inisialisasi CardView
        cvform = findViewById(R.id.cvform)
        cvcalculator = findViewById(R.id.cvcalculator)
        cvkonversi = findViewById(R.id.cvkonversi)
        cvprofile = findViewById(R.id.cvprofile)

        // Intent untuk form activity
        cvform.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Intent untuk kalkulator activity
        cvcalculator.setOnClickListener {
            val intent = Intent(this, Kalkulator2::class.java)
            startActivity(intent)
        }

        // Intent untuk konversi suhu activity
        cvkonversi.setOnClickListener {
            // Pastikan KonversiSuhuConstraint dideklarasikan di AndroidManifest.xml
            val intent = Intent(this, KonversiSuhuConstraint::class.java)
            startActivity(intent)
        }

        // Intent untuk profil activity
        cvprofile.setOnClickListener {
            val intent = Intent(this, ConstraintLayout::class.java) // Ganti dengan aktivitas profil Anda
            startActivity(intent)
        }
    }
}
