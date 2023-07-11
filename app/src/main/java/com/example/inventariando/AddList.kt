package com.example.inventariando

import android.annotation.SuppressLint
import android.content.Context
import android.icu.text.CaseMap.Title
import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        functions = FirebaseFunctions.getInstance()
    }


    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var email = Firebase.auth.currentUser?.email
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_list, container, false)

        val userTitle = view.findViewById<EditText>(R.id.listTitle)
        val save = view.findViewById<Button>(R.id.guardar)
        val cancelar = view.findViewById<Button>(R.id.cancelar)
        //                    listener?.transferMessage(title)
        //                    db.collection("$email").document("$title").set({


        cancelar.setOnClickListener() {
            val cancel = "click"
            listener2?.transferMessage2(cancel)
        }
        save.setOnClickListener {
            val title = userTitle.text.toString()
            checkTitle(title)
        }
        return view
    }

    fun checkTitle(text: String){
            // Create the arguments to the callable function.
        val title = ""
            val data = hashMapOf(
                text to title
            )

            return functions
                .getHttpsCallable("addMessage")
                .call(data)
                .continueWith { task ->
                    // This continuation runs on either success or failure, but if the task
                    // has failed then result will throw an Exception which will be
                    // propagated down.
                    val result = task.result?.data as String
                    result
                }

    }

}

