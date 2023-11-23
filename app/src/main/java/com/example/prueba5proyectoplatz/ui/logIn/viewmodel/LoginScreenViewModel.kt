package com.example.prueba5proyectoplatz.ui.logIn.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prueba5proyectoplatz.model.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class LoginScreenViewModel: ViewModel(){

    private val auth: FirebaseAuth = Firebase.auth
    private val _loading = MutableLiveData(false)

    fun signInWithEmailAndPassword(email: String, password: String, home: () -> Unit)
            = viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener{task ->
                    if (task.isSuccessful){
                        Log.d("Yipii", "signInWithEmailAndPassword Logged IN!!")
                        home()
                    }
                    else{
                        Log.d("Yipii", "signInWithEmailAndPassword: ${task.result.toString()}")

                    }
                }
        }catch (ex:Exception){
            Log.d("Yipii", "signInWithEmailAndPassword ${ex.message}")
        }
    }

    fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        home: () -> Unit
    ){
        if (_loading.value == false){
            _loading.value = true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener{ task ->
                    if (task.isSuccessful){
                        val displayName =
                            task.result.user?.email?.split("@")?.get(0)
                        createuser(displayName)
                        home()
                    }
                    else{
                        Log.d("yipiny", "createuserWithEmailAndPassword: ${task.result.toString()}")
                    }
                    _loading.value = false
                }
        }
    }

    private fun createuser(displayName: String?) {
        val userId = auth.currentUser?.uid
        //val user = mutableMapOf<String, Any>()

        //user["user_id"] = userId.toString()
        //user["display_name"] = displayName.toString()

        val user = User(
            userId = userId.toString(),
            displayName = displayName.toString(),
            id = null
        ).toMap()

        FirebaseFirestore.getInstance().collection("users")
            .add(user)
            .addOnSuccessListener {
                Log.d("Yipint", "Created ${it.id}")
            }.addOnFailureListener {
                Log.d("Yipint", "Error  ${it}")
            }

    }
}