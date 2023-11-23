package com.example.prueba5proyectoplatz.ui.logIn.view

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Visibility
//import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.prueba5proyectoplatz.navigation.AppSreens
import com.example.prueba5proyectoplatz.ui.logIn.viewmodel.LoginScreenViewModel


@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()

){
    //True = Login; False = Create
    val showLoginForm = rememberSaveable{
        mutableStateOf(true)
    }
    Surface(modifier = Modifier
        .fillMaxSize()
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            if (showLoginForm.value){
                Text(text = "Inicia Sesion")
                UserForm(
                    isCreateAccount = false
                ){
                        email, password ->
                    Log.d("Log in", "Logged with $email y $password")
                    viewModel.signInWithEmailAndPassword(email, password){
                        navController.navigate(AppSreens.HomeScreen.route)
                    }
                }
            }
            else{
                Text(text = "Crea una cuenta")
                UserForm(
                    isCreateAccount = true
                ){
                        email, password ->
                    Log.d("Sign in", "Signed with $email y $password")
                    viewModel.createUserWithEmailAndPassword(email, password){
                        navController.navigate(AppSreens.HomeScreen.route)
                    }
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row (
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                val text1 =
                    if (showLoginForm.value) "Don't have an account yet?"
                    else "Have an account already?"
                val text2 =
                    if (showLoginForm.value) "Sign In"
                    else "Log In"
                Text(text = text1)
                Text(text = text2,
                    modifier = Modifier
                        .clickable {showLoginForm.value = !showLoginForm.value}
                        .padding(start = 5.dp),
                    color = MaterialTheme.colorScheme.inversePrimary )

            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserForm(
    isCreateAccount: Boolean = false,
    onDone: (String, String)-> Unit = {email, password ->}
) {
    val email = rememberSaveable {
        mutableStateOf("")
    }

    val password = rememberSaveable {
        mutableStateOf("")
    }

    val passwordVisible = rememberSaveable {
        mutableStateOf(false)
    }

    val valido = remember(email.value, password.value){
        email.value.trim().isNotEmpty()
    }

    val keyboardController = LocalSoftwareKeyboardController.current
    Column(horizontalAlignment = Alignment.CenterHorizontally){
        EmailInput(
            emailState = email
        )
        PasswordInput(
            passwordState = password,
            labelId = "Password",
            passwordVisible = passwordVisible
        )
        SubmitButton(
            textId = if(isCreateAccount) "Crear cuenta" else "Login",
            inputValido = valido
        ){
            onDone(email.value.trim(), password.value.trim())
            keyboardController?.hide()
        }
    }
}

@Composable
fun SubmitButton(
    textId: String,
    inputValido: Boolean,
    onClic: ()-> Unit

) {
    Button(onClick = onClic,
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape,
        enabled = inputValido
    ){
        Text(text = textId,
            modifier = Modifier
                .padding(5.dp)
        )



    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInput(
    passwordState: MutableState<String>,
    labelId: String,
    passwordVisible: MutableState<Boolean>
) {
    val visualTransformation = if (passwordVisible.value)
        VisualTransformation.None
    else PasswordVisualTransformation()

    OutlinedTextField(
        value = passwordState.value ,
        onValueChange = {passwordState.value = it},
        label = { Text(text = labelId)},
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        modifier = Modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        visualTransformation = visualTransformation,
        trailingIcon = {
            if(passwordState.value.isNotBlank()){
//                PasswordvisibleIcon(passwordVisible)
            }
            else null
        }
    )
}

//@Composable
//fun PasswordvisibleIcon(
//    passwordVisible: MutableState<Boolean>
//) {
//    val image =
//        if (passwordVisible.value)
//            Icons.Default.Visibility
//        else
//            Icons.Default.VisibilityOff
//    IconButton(onClick = {
//        passwordVisible.value = !passwordVisible.value
//    }) {
//        Icon(
//            imageVector = image,
//            contentDescription = ""
//        )
//
//    }
//}

@Composable
fun EmailInput(
    emailState: MutableState<String>,
    labelId: String = "Email"
) {
    InputField(
        valueState = emailState,
        labelId = labelId,
        keyboardType = KeyboardType.Email
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    valueState: MutableState<String>,
    labelId: String,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = {valueState.value = it},
        label = { Text(text = labelId)},
        singleLine = isSingleLine,
        modifier = Modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
    )
}

