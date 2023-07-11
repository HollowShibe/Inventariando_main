package com.example.inventariando

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var signIn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<Button>(R.id.btnSignIn) as Button
        btn.setOnClickListener{
            signInLauncher.launch(signInIntent)
        }



    }

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract(),
    ) { res ->
        this.onSignInResult(res)
    }


    val providers = arrayListOf(
      //  AuthUI.IdpConfig.EmailBuilder().build(),
      //  AuthUI.IdpConfig.PhoneBuilder().build(),
        AuthUI.IdpConfig.GoogleBuilder().build(),
      //  AuthUI.IdpConfig.TwitterBuilder().build(),
    )
    val signInIntent = AuthUI.getInstance()
        .createSignInIntentBuilder()
        .setAvailableProviders(providers)
        .build()




    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {

            // Successfully signed in
            val user1 = FirebaseAuth.getInstance().currentUser
            val intent = Intent(this, MainActivity2::class.java)

            val user2 = Firebase.auth.currentUser
            user2?.let {
                // Name, email address, and profile photo Url
                val name = it.displayName
                val email = it.email
                val photoUrl = it.photoUrl

                // Check if user's email is verified
                val emailVerified = it.isEmailVerified

                // The user's ID, unique to the Firebase project. Do NOT use this value to
                // authenticate with your backend server, if you have one. Use
                // FirebaseUser.getIdToken() instead.
                val uid = it.uid

                intent.putExtra("email",email)
            }

            startActivity(intent)

        }
        else {
            val text = "Fallo de inicio de sesión"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(this, text, duration)
            // in Activity
            toast.show()

        }
    }







}