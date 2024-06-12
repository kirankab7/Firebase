package com.example.firebaseprojectnew


import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebaseprojectnew.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            var eName= binding.editName.text.toString().trim()
            var eAdress= binding.editTextAdress.text.toString().trim()
            var ePassword= binding.editTextPassword.text.toString().trim()

            var userMap = hashMapOf(
                "name" to eName,
                "adress" to eAdress,
                "sPassword" to ePassword
            )
            val userId = FirebaseAuth.getInstance().currentUser!!.uid
            db.collection("user").document(userId).set(userMap)
                .addOnSuccessListener {
                    Toast.makeText(this,"Success Add", Toast.LENGTH_LONG).show()

                }
                .addOnFailureListener{
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                }
        }


    }
}