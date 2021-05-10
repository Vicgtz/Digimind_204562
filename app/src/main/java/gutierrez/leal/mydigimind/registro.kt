package gutierrez.leal.mydigimind

import android.R.attr
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class registro : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        mAuth = FirebaseAuth.getInstance();
        val btn_registrar: Button = findViewById(R.id.btn_registrar)

        btn_registrar.setOnClickListener {
            valida_registro()
        }
    }

    private fun valida_registro(){
        val et_correo: EditText= findViewById(R.id.et_correo_reg)
        val et_contra1: EditText= findViewById(R.id.et_contra_reg)
        val et_contra2: EditText= findViewById(R.id.et_contra2_reg)

        var correo: String = et_correo.text.toString()
        var contra1: String = et_contra1.text.toString()
        var contra2: String = et_contra2.text.toString()

        if (!correo.isNullOrBlank() && !contra1.isNullOrBlank() && !contra2.isNullOrBlank()){

            if (contra1 == contra2){

                registrarFirebase(correo, contra1)

            }else{
                Toast.makeText(this, "Las contrase;as no coinciden", Toast.LENGTH_SHORT).show()
            }

        }else{
            Toast.makeText(this, "Ingresa datos", Toast.LENGTH_SHORT).show()
        }

    }

    private fun registrarFirebase(email: String, password: String){
        mAuth!!.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        //Log.d(TAG, "createUserWithEmail:success")
                        val user = mAuth!!.currentUser
                        //updateUI(user)
                        Toast.makeText(baseContext, "${user.email}se ha creado correctamente",
                                Toast.LENGTH_SHORT).show()
                    } else {
                        // If sign in fails, display a message to the user.
                        //Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        //updateUI(null)
                    }
                }
    }

}