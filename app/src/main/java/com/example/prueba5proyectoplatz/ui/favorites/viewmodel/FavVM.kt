package com.example.prueba5proyectoplatz.ui.favorites.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.prueba5proyectoplatz.ui.home.viewmodel.Post

class FavVM: ViewModel() {

}

var favorites = mutableStateListOf<Post>()