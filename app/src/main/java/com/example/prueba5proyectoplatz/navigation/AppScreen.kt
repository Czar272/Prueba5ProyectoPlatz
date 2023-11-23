package com.example.prueba5proyectoplatz.navigation

sealed class AppSreens(val route: String){
    object HomeScreen: AppSreens("home")
    object UploadScreen: AppSreens("agregar")
    object ProfileScreen: AppSreens("perfil")
    object FavScreen: AppSreens("favs")
    object EditProfileScreen: AppSreens("editar perfil")
    object LoginScreen: AppSreens("login")
}
