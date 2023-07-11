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
import android.widget.ListView
import android.widget.Toast
import androidx.core.app.Person
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.AuthUI.TAG
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await

class ListView2 : Fragment() {
    val db = Firebase.firestore
    var email = Firebase.auth.currentUser?.email
    val inventory = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_list_view2, container, false)
        db.collection("$email").get().addOnSuccessListener { result->
            for(document in result){
                val id = document.id
                val inventory = mutableListOf("$id")
                println(inventory)
        val arrayAdapter: ArrayAdapter<*>
        val lvDatos = view.findViewById<ListView>(R.id.lvDatos)
        arrayAdapter = ArrayAdapter(activity as Context,android.R.layout.simple_list_item_1,inventory)
        lvDatos.adapter = arrayAdapter
        lvDatos.setOnItemClickListener(){parent,view,position,id->
                Toast.makeText(activity as Context, parent.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show()
        }
            }

        }

        // Inflate the layout for this fragment
        return view
    }

}