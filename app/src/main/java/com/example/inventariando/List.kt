package com.example.inventariando

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.example.inventariando.databinding.FragmentListBinding
import com.firebase.ui.auth.AuthUI.TAG
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

lateinit var binding: FragmentListBinding

class List : Fragment(), MyInterface {
    val db = Firebase.firestore
    var email = Firebase.auth.currentUser?.email

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("RestrictedApi")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       val fragment = childFragmentManager.findFragmentById(R.id.inputNombre) as AddList
        fragment.listener = this
        fragment.listener2 = this
        Toast.makeText(activity as Context, "Bienvenido de nuevo, $email", Toast.LENGTH_LONG)
            .show()
    }
    override fun transferMessage(title: String) {
        binding.inputNombre.visibility = View.GONE
    }

    override fun transferMessage2(cancel: String) {
        binding.inputNombre.visibility = View.GONE
    }
    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {


        binding = FragmentListBinding.inflate(inflater,container,false)
        binding.addInv.setOnClickListener{
            binding.inputNombre.visibility=View.VISIBLE


        }


      // Inflate the layout for this fragment */


        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.inputNombre.visibility = View.GONE
    }



}