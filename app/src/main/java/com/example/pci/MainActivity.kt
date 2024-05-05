package com.example.pci

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etExamenParcial: EditText = findViewById(R.id.txtExamenParcial)
        val etExamenFinal: EditText = findViewById(R.id.txtExamenFinal)
        val etPep: EditText = findViewById(R.id.txtPep)
        val btnCalcular: Button = findViewById(R.id.btnCalcular)

        btnCalcular.setOnClickListener{
            this.procesaResultados(etExamenParcial.text.toString(),etExamenFinal.text.toString(),etPep.text.toString())
        }
    }
    private fun procesaResultados(examenParcial: String,examenFinal: String,pep: String){
        val pParcial = 0.20
        val pFinal = 0.20
        val pPep = 0.60

        val notaFinal =  (examenParcial.toDouble() * pParcial) + (examenFinal.toDouble() * pFinal) + (pep.toDouble() * pPep)

        var resultado = ""
        if (notaFinal > 10.5){
            resultado = "Aprobado"
        }else{
            resultado = "Desaprobado"
        }

        val intent = Intent(this, ResultadosActivity::class.java)
        intent.putExtra("resultado",resultado)
        intent.putExtra("notaFinal",notaFinal.toString())
        startActivity(intent)
    }
}