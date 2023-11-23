package com.example.prueba5proyectoplatz.ui.upload.view

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.graphics.Insets.add
import android.nfc.Tag
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.prueba5proyectoplatz.bottomBar
import com.example.prueba5proyectoplatz.ui.home.viewmodel.Post
import com.example.prueba5proyectoplatz.ui.home.viewmodel.allPosts
import com.example.prueba5proyectoplatz.ui.upload.viewmodel.UploadVM
import com.example.prueba5proyectoplatz.ui.upload.viewmodel.allFiles
import com.example.prueba5proyectoplatz.ui.upload.viewmodel.myFile

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadScreen(navController: NavHostController) {

    val viewModel = viewModel<UploadVM>()

    var curso by rememberSaveable { mutableStateOf("") }
    var fecha by rememberSaveable { mutableStateOf("") }
    var tema by rememberSaveable { mutableStateOf("") }
    var catedratico by rememberSaveable { mutableStateOf("") }

    var Files by remember {
        mutableStateOf(
            List(allFiles.size) {
                myFile(
                    file = allFiles[it],
                    isSelected = false
                )
            }
        )
    }
    val condicionesCumplidas = curso.isNotBlank() && fecha.isNotBlank() && tema.isNotBlank() && catedratico.isNotBlank()
    val alMenosUnoSeleccionado = Files.any { it.isSelected }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding(),
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                title = { Text(text = "Nueva Publicación") },
                actions = {
                    ClickableText(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(
                                fontStyle = FontStyle.Italic,
                                color = (if (condicionesCumplidas && alMenosUnoSeleccionado) MaterialTheme.colorScheme.onPrimary else Color.LightGray),
                                fontSize = 16.sp
                            )) {
                                append("Agregar")
                            }
                        },
                        onClick = {
                              if(condicionesCumplidas && alMenosUnoSeleccionado){

                                  val newPost = Post(
                                      curso = curso,
                                      tema = tema,
                                      fecha = fecha,
                                      catedratico = catedratico,
                                      usuario = "User592682585",
                                      archivos = Files.filter { it.isSelected }.map { it.file },
                                      isFav = false
                                  )

                                  allPosts.add(0, newPost)

                                  curso=""
                                  tema=""
                                  fecha=""
                                  catedratico=""
                                  Files = Files.map { it.copy(isSelected = false) }
                                  Toast.makeText(context, "Publicación subida correctamente", Toast.LENGTH_SHORT).show()
                              }
                        },
                        modifier = Modifier.padding(end = 10.dp),

                    )
                }
            )
        },
        bottomBar = {
            bottomBar(navController = navController)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 80.dp, bottom = 86.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                TextField(
                    value = curso,
                    onValueChange = { curso = it },
                    label = { Text("Ingrese el curso:")}
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = fecha,
                    onValueChange = { fecha = it },
                    label = { Text("Ingrese la fecha:")}
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = tema,
                    onValueChange = { tema = it },
                    label = { Text("Ingrese el tema:")}
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = catedratico,
                    onValueChange = { catedratico = it },
                    label = { Text("Ingrese el nombre del catedrático:")}
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .height(50.dp)
                        .padding(10.dp)
                ){
                    Text(
                        text = "Tus archivos",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                items(Files.size){ i ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .clickable {
                                Files = Files.mapIndexed { j, item ->
                                    if (i == j) {
                                        item.copy(isSelected = !item.isSelected)
                                    } else item
                                }
                            }
                            .background(if (Files[i].isSelected) Color.LightGray else Color.Transparent)
                    ) {
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
                                .padding(start = 10.dp, end = 10.dp)
                                .wrapContentWidth(Alignment.Start)
                                .scale(3f),
                            contentDescription = null
                        )

                        Column{
                            Text(
                                text = Files[i].file.nombre,
                                fontWeight = FontWeight.Bold
                            )

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    text = "${Files[i].file.cantPaginas} páginas",
                                    fontSize = 12.sp
                                )

                                Spacer(modifier = Modifier.width(10.dp))

                                Text(
                                    text = Files[i].file.peso,
                                    fontSize = 12.sp
                                )

                                Spacer(modifier = Modifier.width(10.dp))

                                Text(
                                    text = Files[i].file.tipo,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}