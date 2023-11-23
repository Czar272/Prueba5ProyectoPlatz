package com.example.prueba5proyectoplatz.ui.upload.viewmodel

import androidx.lifecycle.ViewModel

class UploadVM: ViewModel() {

}

data class File(
    val nombre: String,
    val cantPaginas: String,
    val peso: String,
    val tipo: String
)

data class myFile(
    val file: File,
    val isSelected: Boolean
)

val allFiles = listOf(
    File(
        nombre = "Nueva Nota 2023-04-08",
        cantPaginas = "6",
        peso = "670 kB",
        tipo = "PDF"
    ),
    File(
        nombre = "Nueva Nota 2022-11-13",
        cantPaginas = "3",
        peso = "250 kB",
        tipo = "PDF"
    ),
    File(
        nombre = "Apuntes Ecuas 1",
        cantPaginas = "5",
        peso = "630 kB",
        tipo = "PDF"
    ),
    File(
        nombre = "Mate Shhh Clase 2",
        cantPaginas = "9",
        peso = "860 kB",
        tipo = "PDF"
    ),
    File(
        nombre = "Nueva Nota 2023-09-21",
        cantPaginas = "12",
        peso = "1 MB",
        tipo = "PDF"
    )
)