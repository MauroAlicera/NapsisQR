package com.example.napsisqr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var firebaseAuth:FirebaseAuth
    private lateinit var authStateListener:FirebaseAuth.AuthStateListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnIngresar : Button = findViewById(R.id.btnIngresar)
        val txtemail : TextView = findViewById(R.id.edtEmail)
        val txtpassword : TextView = findViewById(R.id.edtPassword)
        val btnCrear_CuentaNueva :TextView =findViewById(R.id.btnCrearCuenta)
        val btnRecordar : TextView = findViewById(R.id.btnOlvidar)
        firebaseAuth= Firebase.auth

        btnIngresar.setOnClickListener()
        {
            signIn(txtemail.text.toString(),txtpassword.text.toString())
        }
        //como pasar de una interfaz a otra interfaz
        btnCrear_CuentaNueva.setOnClickListener()
        {
            val i = Intent(this,CrearCuenta::class.java)
            startActivity(i)
        }
        btnRecordar.setOnClickListener()
        {
            val i = Intent(this,RecuperarPassword::class.java)
            startActivity(i)
        }
    }


    private fun signIn(email:String,password :String)
    {
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this)
        {
                task->
            if(task.isSuccessful)
            {
                val user = firebaseAuth.currentUser
                val verifica = user?.isEmailVerified
                if(verifica==true){
                    //aqui vamos a ir a la segunda activity
                    val i = Intent(this,InterfazOpciones::class.java)
                    startActivity(i)
                }
                else
                {
                    Toast.makeText(baseContext,"No ha verificado su Correo",Toast.LENGTH_SHORT).show()
                }


            }
            else
            {
                Toast.makeText(baseContext,"Error de email y/o contraseña",Toast.LENGTH_SHORT).show()
            }
        }


    }
}