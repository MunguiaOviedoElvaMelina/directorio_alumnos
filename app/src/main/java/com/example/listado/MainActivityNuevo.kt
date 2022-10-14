package com.example.listado

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.listado.databinding.ActivityMainBinding
import com.example.listado.databinding.ActivityMainNuevoBinding

class MainActivityNuevo : AppCompatActivity() {
    private lateinit var binding: ActivityMainNuevoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainNuevoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var dbconex= DBHelperAlumno(this)
        binding.btnGuardar.setOnClickListener {
            var db = dbconex.writableDatabase

            val txtNom = binding.txtNombre.text.toString()
            val txtCue = binding.txtCuenta.text.toString()
            val txtCorr = binding.txtCorreo.text.toString()
            val txtImg = binding.txtImage.text.toString()

            /*val sql="INSERT INTO alumnos (nombre, cuenta, correo, imagen) VALUES('$txtNom','$txtCue','$txtNom')"
        val status =db.execSQL(sql)*/

            val newReg = ContentValues()
            newReg.put("nombre", txtNom) //nombre de la base de datos, de la interfaz
            newReg.put("cuenta", txtCue)
            newReg.put("correo", txtCorr)
            newReg.put("imagen", txtImg)
            val res = db.insert("alumnos", null, newReg)
            if (res.toInt()==-1){
                Toast.makeText(this, "no se inserto el registro ",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "s inserto el registro ",Toast.LENGTH_LONG).show()
            }
            db.close()
        }

        dbconex.close()


         /*


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

        }*/

    }
}