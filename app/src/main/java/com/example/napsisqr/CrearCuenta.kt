package com.example.napsisqr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CrearCuenta : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cuenta)
        val txtemail_nuevo :TextView = findViewById(R.id.edtEmailNueva)
        val txtPassword_nueva1 :TextView = findViewById(R.id.edtPasswordNueva1)
        val txtPassword_nueva2 :TextView = findViewById(R.id.edtPasswordNueva2)
        val btnCrear : Button = findViewById(R.id.btnCrearCuentaNueva)
        btnCrear.setOnClickListener()
        {
            var pass1 = txtPassword_nueva1.text.toString()
            var pass2 = txtPassword_nueva2.text.toString()
            if(pass1.equals(pass2))
            {
              createAccount(txtemail_nuevo.text.toString(),txtPassword_nueva1.text.toString())
            }
            else
            {
                Toast.makeText(baseContext,"Error : los password no coinciden ",Toast.LENGTH_SHORT).show()
                txtPassword_nueva1.requestFocus()
            }
        }


        firebaseAuth= Firebase.auth
    }
    private fun createAccount(email:String,password :String)
    {
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this){
            task ->
            if(task.isSuccessful)
            {   sendEmailVerification()
                Toast.makeText(baseContext,"Cuenta Creada Correctamente, se requiere verificacion",Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(baseContext,"Algo Salio mal , Error" +task.exception,Toast.LENGTH_SHORT).show()
            }
        }
    }
    private  fun sendEmailVerification ()
    {
        val user = firebaseAuth.currentUser!!
        user.sendEmailVerification().addOnCompleteListener(this)
        {task->

        }

    }
}