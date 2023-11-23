package com.example.prueba5proyectoplatz

import android.R
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.prueba5proyectoplatz.navigation.AppSreens

@Composable
fun bottomBar(navController: NavHostController){
    BottomAppBar(
        actions = {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 50.dp, end = 50.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.navigate(route = AppSreens.HomeScreen.route) }) {
                    Icon(
                        painter = painterResource(id = LocalContext.current.resources.getIdentifier("home", "drawable", LocalContext.current.packageName)),
                        modifier = Modifier
                            .size(30.dp),
                        contentDescription = null
                    )
                }
                IconButton(onClick = { navController.navigate(route = AppSreens.UploadScreen.route) }) {
                    Icon(
                        painter = painterResource(id = LocalContext.current.resources.getIdentifier("add", "drawable", LocalContext.current.packageName)),
                        modifier = Modifier
                            .size(38.dp),
                        contentDescription = null
                    )
                }
                IconButton(onClick = { navController.navigate(route = AppSreens.ProfileScreen.route) }) {
                    Icon(
                        painter = painterResource(id = LocalContext.current.resources.getIdentifier("profile_guy", "drawable", LocalContext.current.packageName)),
                        modifier = Modifier
                            .size(30.dp),
                        contentDescription = null
                    )
                }
            }
        }
    )
}

@Composable
fun SwitchButton(
    switchPadding: Dp,
    buttonWidth: Dp,
    buttomHeight: Dp,
    value: Boolean
){
    val switchSize by remember{
        mutableStateOf(buttomHeight-switchPadding*2)
    }

    val interactionSource = remember {
        MutableInteractionSource()
    }

    var switchClicked by remember{
        mutableStateOf(value)
    }

    var padding by remember {
        mutableStateOf(0.dp)
    }

    padding = if(switchClicked) buttonWidth-switchSize-switchPadding*2 else 0.dp

    val animateSize by animateDpAsState(
        targetValue = if (switchClicked) padding else 0.dp,
        tween(
            durationMillis = 700,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        )
    )

    Box(
        modifier = Modifier
            .width(buttonWidth)
            .height(buttomHeight)
            .clip(CircleShape)
            .background(if (switchClicked) colorScheme.primary else Color.LightGray)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                switchClicked = !switchClicked
            }
    ){
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(switchPadding)
        ) {
            Box(modifier = Modifier
                .fillMaxHeight()
                .width(animateSize)
                .background(Color.Transparent)
            )

            Box(modifier = Modifier
                .size(switchSize)
                .clip(CircleShape)
                .background(Color.White)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topBar(navController: NavHostController, nombre: String){
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding(),
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = colorScheme.primary,
            titleContentColor = colorScheme.onPrimary,
            navigationIconContentColor = colorScheme.onPrimary
        ),
        navigationIcon = {
            IconButton(onClick = { navController.navigate(route = AppSreens.ProfileScreen.route) })
            {
                Icon(
                    painter = painterResource(id = LocalContext.current.resources.getIdentifier("left_arrow", "drawable", LocalContext.current.packageName)),
                    modifier = Modifier
                        .size(30.dp),
                    contentDescription = null
                )
            }
        },
        title = { Text(text = nombre) }
    )
}