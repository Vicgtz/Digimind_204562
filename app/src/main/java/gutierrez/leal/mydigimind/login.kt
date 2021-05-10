package gutierrez.leal.mydigimind

import android.R.attr
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class login : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null

   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

       mAuth = FirebaseAuth.getInstance();

        val btn_registrarse: Button= findViewById(R.id.btn_registrarse)
        val btn_contra: TextView = findViewById(R.id.tv_olvidasteContra)
        val btn_ingresar: Button = findViewById(R.id.btn_ingresar)

        btn_registrarse.setOnClickListener {
            val intent: Intent = Intent(this, registro::class.java)
            startActivity(intent)
        }

        btn_contra.setOnClickListener {
            val intent: Intent = Intent(this, contrasena::class.java)
            startActivity(intent)
        }

        btn_ingresar.setOnClickListener {
            valida_ingreso()
        }
    }

    private fun valida_ingreso(){
        val et_correo: EditText = findViewById(R.id.et_correo)
        val et_contra: EditText = findViewById(R.id.et_contra)

        var correo: String = et_correo.text.toString()
        var contra: String = et_contra.text.toString()

        if (!correo.isNullOrBlank() && !contra.isNullOrBlank()){

            ingresarFirebase(correo,contra)

        } else{
            Toast.makeText(
                this, "Ingresar datos",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    private fun ingresarFirebase(email: String, password: String){
        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                   // Log.d(TAG, "signInWithEmail:success")
                    val user = mAuth!!.currentUser
                    //updateUI(user)
                    val intent: Intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.
                    //Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    //updateUI(null)
                }
            }
    }




}