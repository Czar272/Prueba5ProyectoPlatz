package com.example.prueba5proyectoplatz.ui.editProfile.view

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.prueba5proyectoplatz.topBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditProfileScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            topBar(navController = navController, nombre = "Editar Perfil")
        }
    ) {
        Column (modifier = Modifier
            .padding(top = 64.dp)
            .fillMaxWidth())
        {
            option(nombre = "Cambiar foto", color = Color.Black)
            option(nombre = "Cambiar fondo", color = Color.Black)
            option(nombre = "Cambiar nombre", color = Color.Black)
            option(nombre = "Cerrar sesión", color = Color.Red)
        }
    }
}

@Composable
fun option(nombre: String, color: Color){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clickable {
                /*TODO*/
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = nombre,
            modifier = Modifier
                .padding(start = 30.dp),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
    }

    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
    )
}