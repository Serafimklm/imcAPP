package com.example.caucladoraimc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var buttonCalcularimc: Button
    private lateinit var editPeso: EditText
    private lateinit var editAltura: EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//------------------configurando botao para acessar outra activity----------------------------
        // para que seja possivel abir uma nova activity por meio do botao calcular IMC
        buttonCalcularimc = findViewById(R.id.button_calcularimc)
        editPeso = findViewById(R.id.edit_peso)
        editAltura = findViewById(R.id.edit_altura)

        buttonCalcularimc.setOnClickListener {

            var intent = Intent(this, ResultadoActivity::class.java);


            val peso = editPeso.text.toString()
            val altura = editAltura.text.toString()

            if  (peso.isNotEmpty() && altura.isNotEmpty()){

                //convertendo peso e altura para double
                intent.putExtra("peso", peso.toDouble())
                intent.putExtra("altura", altura.toDouble())}

            startActivity(intent);

        }


//--------------------------------------------------------------------------------------------
        }
}