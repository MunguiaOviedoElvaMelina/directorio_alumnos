package com.example.listado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listado.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    val Talumnos =ArrayList <Alumno>()

    val adapter= AlumnoAdapter(this,Talumnos)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recycler: RecyclerView= binding.recycle
        val faButton = binding.faButton

        //creamos la conexion
        val dbconex= DBHelperAlumno(this)
        //abrimos la base de dato para leer
        val db= dbconex.readableDatabase
        //declaramos un cursor oara recorrer los registros en la table
        val cursor= db.rawQuery("SELECT * FROM alumnos", null)

        if (cursor.moveToFirst())


        recycler.layoutManager = LinearLayoutManager(this)


/*
        Talumnos.add(Alumno("https://w7.pngwing.com/pngs/317/819/png-transparent-hermione-granger-with-book.png","Fabiola","20176989","mfabiola8@ucol.mx"))
        Talumnos.add(Alumno("https://e7.pngegg.com/pngimages/847/128/png-clipart-harry-potter-literary-series-garri-potter-j-k-rowling-lord-voldemort-harry-potter-and-the-philosopher-s-stone-hogwarts-train.png","daniel","20189885","rdaniel@ucol.mx"))
        Talumnos.add(Alumno("https://w7.pngwing.com/pngs/317/819/png-transparent-hermione-granger-with-book.png","diana","20199885","gdaniela6@ucol.mx"))
        Talumnos.add(Alumno("https://www.pngitem.com/pimgs/m/234-2342530_dracomalfoy-draco-malfoy-remixit-freetoedit-remixme-draco-malfoy.png","ulises","20156553","oulises4@ucol.mx"))
        Talumnos.add(Alumno("https://e7.pngegg.com/pngimages/847/128/png-clipart-harry-potter-literary-series-garri-potter-j-k-rowling-lord-voldemort-harry-potter-and-the-philosopher-s-stone-hogwarts-train.png","josew","20158945","vjosew58@ucol.mx"))
*/

        recycler.adapter = adapter

        adapter.setOnIteamClickListener(object : AlumnoAdapter.ClickListener {
            override fun onItemClick(view: View, position: Int) {
               Toast.makeText(this@MainActivity, "click en el item: ${position}", Toast.LENGTH_LONG).show()
                itemOptionsMenu(position)
            }
        })

        //variable para recibir extras
        val parExtras= intent.extras
        val msj=parExtras?.getString("mensaje")
        val nombre=parExtras?.getString("nombre")
        val cuenta=parExtras?.getString("cuenta")
        val correo=parExtras?.getString("correo")
        val imagen=parExtras?.getString("imagen")
        // updateIndex: Int =get.intent.extra("idA",0)

        if (msj=="Nuevo"){
            val insertIndex: Int = Talumnos.count()
            Talumnos.add(insertIndex,
                Alumno(
                    "${imagen}",
                    "${nombre}",
                    "${cuenta}",
                    "${correo}"
                )

            )
            adapter.notifyItemInserted(insertIndex)
        } /*else if (msj=="editar"){
            Talumnos.set(updateIndex,
            Alumno(
                "${imagen}",
                "${nombre}",
                "${cuenta}",
                "${correo}"

            )
                )
        }*/

        //click en el boton
        faButton.setOnClickListener{

            val intento1= Intent(this, MainActivityNuevo::class.java)
            //intento1.putExtra("valor1", "HOLA MUNDO")
            startActivity(intento1)


        }

    }

    private fun itemOptionsMenu(position: Int) {
        val popupMenu =
            PopupMenu(this, binding.recycle[position].findViewById(R.id.textViewOptions))
        popupMenu.inflate(R.menu.options_menu)
        //Para cambiarnos de activity
        val intento2 = Intent(this, MainActivityNuevo::class.java)
        //Implementar el click en el item
        popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                when (item?.itemId) {
                    R.id.borrar -> {
                        val tmpAlum = Talumnos[position]
                        Talumnos.remove(tmpAlum)
                        adapter.notifyDataSetChanged()
                        return true
                    }
                    R.id.editar -> {
                        //Tomamos los datos del alumno, en la posición de la lista donde hicieron click
                        val nombre = Talumnos[position].nombre
                        val cuenta = Talumnos[position].cuenta
                        val correo = Talumnos[position].correo
                        val image = Talumnos[position].image
                        //En position tengo el indice del elemento en la lista
                        val idAlum: Int = position
                        intento2.putExtra("mensaje", "edit")
                        intento2.putExtra("nombre", "${nombre}")
                        intento2.putExtra("cuenta", "${cuenta}")
                        intento2.putExtra("correo", "${correo}")
                        intento2.putExtra("imagen", "${image}")
                        //Pasamos por extras el idAlum para poder saber cual editar de la lista (ArrayList)
                        intento2.putExtra("idA", idAlum)
                        startActivity(intento2)
                        return true
                    }
                }
                return false
            }
        })
        popupMenu.show()
    }


    }

