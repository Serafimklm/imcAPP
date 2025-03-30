package com.example.caucladoraimc

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.text.toDouble

class ResultadoActivity : AppCompatActivity() {

    // Declaração de variáveis para os TextViews onde serão exibidos os resultados
    private lateinit var textAltura: TextView
    private lateinit var textPeso: TextView
    private lateinit var textDiagnostico: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Habilita o layout para ocupar a tela inteira, escondendo as barras do sistema
        setContentView(R.layout.activity_resultado) // Define o layout da tela como activity_resultado.xml

        // Ajusta os paddings para evitar sobreposição com as barras do sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Associa os elementos do layout às variáveis declaradas acima
        textPeso = findViewById(R.id.textPeso)
        textAltura = findViewById(R.id.textAltura)
        textDiagnostico = findViewById(R.id.textDiagnostico)

        // Obtém os valores passados da Activity anterior por meio do Intent
        val bundle = intent.extras
        if (bundle != null) {
            val peso = bundle.getDouble("peso") // Obtém o peso passado como argumento
            val altura = bundle.getDouble("altura") // Obtém a altura passada como argumento

            // Exibe os valores informados na interface do usuário
            textPeso.text = "Peso informado $peso KG"
            textAltura.text = "Altura informada $altura m"

            // Cálculo do IMC: peso dividido pelo quadrado da altura
            val imc = peso.toDouble() / (altura.toDouble() * altura.toDouble())
            println(imc)

            // Determina a classificação do IMC com base no valor calculado, variavel resultado vai conter o diagnostico
            val resultado = if (imc < 18.5) {
                "Abaixo do peso"
            } else if (imc >= 18.5 && imc <= 24.9) {
                "Peso normal"
            } else if (imc >= 25 && imc <= 29.9) {
                "Sobrepeso"
            } else if (imc >= 30 && imc <= 34.9) {
                "Obesidade grau 1"
            } else {
                "Obesidade extrema"
            }

            // Exibe o diagnóstico do IMC na interface
            textDiagnostico.text = resultado as CharSequence?
        }
    }
}
