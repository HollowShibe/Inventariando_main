package com.example.inventariando

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.*
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.inventariando.databinding.ActivityMainBinding
import com.example.inventariando.databinding.FragmentListBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.Locale.Category

class MainActivity2 : AppCompatActivity(), MyInterface {
    private lateinit var navController: NavController
    private lateinit var binding: FragmentListBinding
    lateinit var inputNombre: FragmentContainerView
    lateinit var textTitle: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        val email = intent.getStringExtra("email")
        val editor = getSharedPreferences("preferences", Context.MODE_PRIVATE).edit()
        editor.putString("email", null)
        editor.commit()


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        setupWithNavController(bottomNavigationView, navController)

    }



}