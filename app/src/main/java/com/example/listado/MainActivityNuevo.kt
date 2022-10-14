package com.example.listado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.listado.databinding.ActivityMainBinding
import com.example.listado.databinding.ActivityMainNuevoBinding

class MainActivityNuevo : AppCompatActivity() {
    private lateinit var binding: ActivityMainNuevoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainNuevoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val txtNom= binding.txtNombre.text
        val txtCue= binding.txtCuenta.text
        val txtCorr=binding.txtCorreo.text
        val txtImg=binding.txtImage.text
        //recepcion de extras
        val parExtra = intent.extras
        var  miVar=  parExtra?.getString("idA")


      /* val parExtra = intent.extras
      var  miVar=  parExtra?.getString("valor1")
      binding.txtTitulo.text=miVar.toString()*/

        binding.btnGuardar.setOnClickListener {


            val intento2 = Intent(this,MainActivity::class.java)
            intento2.putExtra("mensaje", "Nuevo")
            intento2.putExtra("nombre", "${txtNom}")
            intento2.putExtra("cuenta", "${txtCue}")
            intento2.putExtra("correo", "${txtCorr}")
            intento2.putExtra("imagen", "${txtImg}")
            startActivity(intento2)

        }

    }
}