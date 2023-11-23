package com.example.prueba5proyectoplatz.ui.profile.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.prueba5proyectoplatz.R
import com.example.prueba5proyectoplatz.SwitchButton
import com.example.prueba5proyectoplatz.bottomBar
import com.example.prueba5proyectoplatz.navigation.AppSreens

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(navController: NavHostController){
    Scaffold(
        bottomBar = {
            bottomBar(navController = navController)
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(Color.LightGray)
            ) {
                Image(
                    painter = painterResource(
                        id = LocalContext.current.resources.getIdentifier(
                            "profile_background",
                            "drawable",
                            LocalContext.current.packageName
                        )
                    ),
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.profile_pic),
                    modifier = Modifier
                        .size(200.dp)
                        .align(Alignment.Center)
                        .padding(16.dp),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                Text(
                    text = "User592682585",
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(10.dp),
                    color = Color.Black,
                    fontSize = 30.sp,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(125.dp)
                        .clickable {
                            navController.navigate(route = AppSreens.EditProfileScreen.route)
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Image(
                        painter = painterResource(id = R.drawable.profile_guy),
                        contentDescription = null,
                        modifier = Modifier
                            .size(90.dp)
                            .padding(start = 45.dp)
                            .wrapContentWidth(Alignment.Start)
                            .scale(4f)
                    )

                    Text(
                        text = "Editar Perfil",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 25.sp
                    )

                    Spacer(modifier = Modifier.width(30.dp))

                    Image(
                        painter = painterResource(id = R.drawable.triangulo),
                        contentDescription = null,
                        modifier = Modifier
                            .size(60.dp)
                            .padding(end = 45.dp)
                            .wrapContentWidth(Alignment.End)
                            .scale(2f)
                    )
                }

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(125.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Image(
                        painter = painterResource(id = R.drawable.notification_bell),
                        contentDescription = null,
                        modifier = Modifier
                            .size(90.dp)
                            .padding(start = 45.dp)
                            .wrapContentWidth(Alignment.Start)
                            .scale(4f)
                    )

                    Text(
                        text = "Notificaciones",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 25.sp
                    )

                    SwitchButton(switchPadding = 5.dp, buttonWidth = 70.dp, buttomHeight = 40.dp, value = false)
                    Spacer(modifier = Modifier.width(1.dp))
                }

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(125.dp)
                        .clickable {
                            navController.navigate(route = AppSreens.FavScreen.route)
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Image(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = null,
                        modifier = Modifier
                            .size(90.dp)
                            .padding(start = 45.dp)
                            .wrapContentWidth(Alignment.Start)
                            .scale(3f)
                    )

                    Text(
                        text = "Favoritos",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 25.sp
                    )

                    Spacer(modifier = Modifier.width(30.dp))

                    Image(
                        painter = painterResource(id = R.drawable.triangulo),
                        contentDescription = null,
                        modifier = Modifier
                            .size(60.dp)
                            .padding(end = 45.dp)
                            .wrapContentWidth(Alignment.End)
                            .scale(2f)
                    )
                }
            }
        }
    }
}

