package com.example.act20_simplecoroutines_guillemdehon

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var downloadButton: Button
    private lateinit var downloadStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        downloadButton = findViewById(R.id.btnStartDownload)
        downloadStatus = findViewById(R.id.tvDownloadStatus)

        downloadButton.setOnClickListener {
            startDownload()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun startDownload() {
        // Mostrem un toast i canviem l'estat
        Toast.makeText(this, "Descarregant...", Toast.LENGTH_SHORT).show()
        downloadStatus.text = "Estat: Descarregant..."

        // Creem un fil nou per simular la descàrrega
        Thread {
            // Simulem un temps de descàrrega aleatori
            val downloadTime = (2000..5000).random().toLong() // entre 2 i 5 segons
            try {
                Thread.sleep(downloadTime) // Simulació de descàrrega amb un retard
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            // Un cop acabat el "download", actualitzem la UI
            val mainHandler = Handler(Looper.getMainLooper()) // Handler per actualitzar la UI al fil principal
            mainHandler.post {
                downloadStatus.text = "Estat: Descarregat correctament!"
                Toast.makeText(applicationContext, "Descarregat correctament!", Toast.LENGTH_SHORT).show()
            }
        }.start() // Iniciem el fil
    }
}
