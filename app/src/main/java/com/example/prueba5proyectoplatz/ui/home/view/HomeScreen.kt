package com.example.prueba5proyectoplatz.ui.home.view

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.prueba5proyectoplatz.bottomBar
import com.example.prueba5proyectoplatz.ui.favorites.viewmodel.favorites
import com.example.prueba5proyectoplatz.ui.home.viewmodel.HomeVM
import com.example.prueba5proyectoplatz.ui.home.viewmodel.Post
import com.example.prueba5proyectoplatz.ui.home.viewmodel.allPosts
import java.util.Collections.copy

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController){
    val viewModel = viewModel<HomeVM>()
    val searchText by viewModel.searchText.collectAsState()
    val posts by viewModel.posts.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()

    Scaffold(
        bottomBar = {
            bottomBar(navController = navController)
        })
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 86.dp, start = 16.dp, end = 16.dp)
        ){

            TextField(
                value = searchText,
                onValueChange = viewModel::onSearchTextChange,
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        painter = painterResource(
                            id = LocalContext.current.resources.getIdentifier(
                                "lupa",
                                "drawable",
                                LocalContext.current.packageName
                            )
                        ),
                        modifier = Modifier
                            .size(30.dp),
                        contentDescription = null
                    )
                },
                placeholder = { Text(text = "Buscar") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            if(isSearching){
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    items(posts) { post ->
                        addCard(post = post, index = 0)
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun addCard(post: Post, index: Int) {

    val usuario = post.usuario
    val curso = post.curso
    val tema = post.tema
    val fecha = post.fecha
    val catedratico = post.catedratico
    val archivos = post.archivos
    var isFav by remember { mutableStateOf(post.isFav) }

    var esVisible by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    Card(
        shape = RoundedCornerShape(15.dp)
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painter = painterResource(
                        id = LocalContext.current.resources.getIdentifier(
                            "profile_pic",
                            "drawable",
                            LocalContext.current.packageName
                        )
                    ),
                    modifier = Modifier
                        .size(30.dp),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "$usuario  -  2h",
                    modifier = Modifier.weight(1f)
                )

                IconButton(
                    onClick = {
                        esVisible = !esVisible
                    }
                ){
                    Icon(
                        painter = painterResource(
                            id = LocalContext.current.resources.getIdentifier(
                                "tres_puntos",
                                "drawable",
                                LocalContext.current.packageName
                            )
                        ),
                        modifier = Modifier
                            .size(30.dp)
                            .wrapContentWidth(Alignment.End),
                        contentDescription = null
                    )

                    DropdownMenu(
                        expanded = esVisible,
                        onDismissRequest = { esVisible = false },
                        modifier = Modifier.wrapContentWidth(Alignment.End)
                    ){
                        DropdownMenuItem(
                            text = {Text(text = if (isFav) "Eliminar de Favoritos" else "Agregar a Favoritos")},
                            onClick = {
                                isFav = !isFav
                                post.isFav = isFav
                                if (isFav) {
                                    favorites.add(0,post)
                                    Toast.makeText(context, "Se añadió a favoritos", Toast.LENGTH_SHORT).show()
                                } else {
                                    favorites.remove(post)
                                    Toast.makeText(context, "Se eliminó de favoritos", Toast.LENGTH_SHORT).show()
                                }
                            }
                        )

                        DropdownMenuItem(
                            text = { Text(text = "Reportar") },
                            onClick = { Toast.makeText(context, "Gracias por reportar", Toast.LENGTH_SHORT).show() }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            val texto = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Curso:")
                }
                append(" $curso \n")

                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Fecha:")
                }
                append(" $fecha \n")

                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Tema:")
                }
                append(" $tema \n")

                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Catedrático:")
                }
                append(" $catedratico")
            }

            Text(
                text = texto,
                modifier = Modifier.padding(start = 30.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyRow{
                items(archivos){archivo ->

                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray)
                    ){
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 40.dp, end = 40.dp, top = 10.dp, bottom = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Icon(
                                painter = painterResource(
                                    id = LocalContext.current.resources.getIdentifier(
                                        "pdf_sign",
                                        "drawable",
                                        LocalContext.current.packageName
                                    )
                                ),
                                modifier = Modifier
                                    .size(30.dp)
                                    .wrapContentWidth(Alignment.Start)
                                    .padding(end = 20.dp)
                                    .scale(3f),
                                contentDescription = null
                            )

                            Column{
                                Text(
                                    text = archivo.nombre,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp
                                )

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Text(
                                        text = "${archivo.cantPaginas} páginas",
                                        fontSize = 12.sp
                                    )

                                    Spacer(modifier = Modifier.width(10.dp))

                                    Text(
                                        text = archivo.peso,
                                        fontSize = 12.sp
                                    )

                                    Spacer(modifier = Modifier.width(10.dp))

                                    Text(
                                        text = archivo.tipo,
                                        fontSize = 12.sp
                                    )
                                }
                            }

                            IconButton(onClick = {  })
                            {
                                Icon(
                                    painter = painterResource(
                                        id = LocalContext.current.resources.getIdentifier(
                                            "download",
                                            "drawable",
                                            LocalContext.current.packageName
                                        )
                                    ),
                                    modifier = Modifier
                                        .size(30.dp)
                                        .wrapContentWidth(Alignment.End)
                                        .padding(start = 17.dp)
                                        .scale(3f),
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            }

        }
    }
}