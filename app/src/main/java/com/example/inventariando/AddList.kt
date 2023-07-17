package com.example.inventariando

import android.annotation.SuppressLint
import android.content.Context
import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.os.Message
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentContainerView
import com.example.inventariando.databinding.FragmentAddListBinding
import com.example.inventariando.databinding.FragmentListBinding
import com.google.android.gms.tasks.Task
import com.google.android.play.core.integrity.e
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.FirebaseFunctionsException
import com.google.firebase.functions.ktx.functions
import com.google.firebase.ktx.Firebase
import java.util.Locale.Category

class AddList : Fragment() {
    val db = Firebase.firestore
    var listener: MyInterface? = null
    var listener2: MyInterface? = null
    private lateinit var functions: FirebaseFunctions
    var email = Firebase.auth.currentUser?.email

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        functions = FirebaseFunctions.getInstance()
    }


    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_list, container, false)

        val userTitle = view.findViewById<EditText>(R.id.listTitle)
        val save = view.findViewById<Button>(R.id.guardar)
        val cancelar = view.findViewById<Button>(R.id.cancelar)
        //                    listener?.transferMessage(title)


        cancelar.setOnClickListener() {
            val cancel = "click"
            listener2?.transferMessage2(cancel)
        }
        save.setOnClickListener {
            val title = userTitle.text.toString()
            var code: String = addTitlePy(title, "$email").toString()
                if(code == "300"){
                    Toast.makeText(activity as Context, "El inventario ya existe, no se pudo agregar", Toast.LENGTH_LONG)
                        .show()

                }else{
                    Toast.makeText(activity as Context, "El inventario $title se ha agregado con éxito!", Toast.LENGTH_LONG).show()

                }
            listener?.transferMessage(title)
        }

        return view
    }

    private fun addTitle(title: String): Task<String> {
        // Create the arguments to the callable function.
        val data = hashMapOf(
            "title" to title
        )
        return functions
            .getHttpsCallable("testfun")
            .call(data)
            .continueWith { task ->
                val result = task.result?.data as String
                result
            }

    }

    private fun addTitlePy(title: String, email: String): Task<String> {
        // Create the arguments to the callable function.
        val data = hashMapOf(
            "title" to title,
            "email" to email
        )
        return functions
            .getHttpsCallable("addnumbers")
            .call(data)
            .continueWith { task ->
                val result = task.result?.data as String
                result
            }

    }


}

