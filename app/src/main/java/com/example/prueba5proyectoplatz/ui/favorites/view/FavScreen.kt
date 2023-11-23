package com.example.prueba5proyectoplatz.ui.favorites.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.prueba5proyectoplatz.topBar
import com.example.prueba5proyectoplatz.ui.favorites.viewmodel.favorites
import com.example.prueba5proyectoplatz.ui.home.view.addCard
import com.example.prueba5proyectoplatz.ui.home.viewmodel.allPosts

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            topBar(navController = navController, nombre = "Favoritos")
        }
    ) {
        if(favorites.isEmpty()){
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Aún no tienes favoritos agregados",
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.LightGray,
                    fontStyle = FontStyle.Italic,
                    fontSize = 20.sp
                )
            }
        } else{
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 80.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    items(favorites) { favorite ->
                        addCard(post = favorite, index = 0)
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}