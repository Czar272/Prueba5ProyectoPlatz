package com.example.prueba5proyectoplatz.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prueba5proyectoplatz.ui.upload.viewmodel.File
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class HomeVM: ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _posts = MutableStateFlow(allPosts)
    val posts = searchText
        //.debounce(1000L) //Esto es para cuando haya con que conectarlo
        .onEach { _isSearching.update { true } }
        .combine(_posts){ text, posts ->
            if(text.isBlank()){
                posts
            } else {
                delay(2000L)
                posts.filter {
                    it.matchSearch(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _posts.value
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }
}

data class Post(
    val curso: String,
    val tema: String,
    val fecha: String,
    val catedratico: String,
    val usuario: String,
    val archivos: List<File>,
    var isFav: Boolean
) {
    fun matchSearch(query: String): Boolean {
        val matchingCombos = listOf(
            "$curso",
            "$tema",
            "$fecha",
            "$catedratico",
            "$usuario" //Puedo agregar más formas de buscar pero por ahora que se quede así
        )

        return matchingCombos.any {
            it.contains(query, ignoreCase = true)
        }
    }
}

val fakeFiles = listOf(
    listOf(
        File(
            nombre = "Nueva Nota 2023-09-18",
            cantPaginas = "2",
            peso = "230 kB",
            tipo = "PDF"
        )
    ),
    listOf(
        File(
            nombre = "Clase dielectricos",
            cantPaginas = "1",
            peso = "100 kB",
            tipo = "PDF"
        )
    ),
    listOf(
        File(
            nombre = "Clase MCD lunes",
            cantPaginas = "1",
            tipo = "PDF",
            peso = "150 kB"
        ),
        File(
            nombre = "Clase MCD miercoles",
            cantPaginas = "2",
            tipo = "PDF",
            peso = "210 kB"
        )
    ),
    listOf(
        File(
            nombre = "Clase lunes",
            cantPaginas = "2",
            peso = "248 kB",
            tipo = "PDF"
        ),
        File(
            nombre = "Clase miercoles",
            cantPaginas = "2",
            peso = "230 kB",
            tipo = "PDF"
        ),
        File(
            nombre = "Clase viernes",
            cantPaginas = "1",
            peso = "180 kB",
            tipo = "PDF"
        )
    ),
    listOf(
        File(
            nombre = "Nueva Nota 2022-06-03",
            cantPaginas = "1",
            peso = "164 kB",
            tipo = "PDF"
        )
    )
)

var allPosts = mutableListOf(
    Post(
        curso = "Ecuaciones diferenciales",
        tema = "Laplace",
        fecha = "18/09/2023",
        catedratico = "JP",
        usuario = "DonChecho",
        archivos = fakeFiles[0],
        isFav = false
    ),
    Post(
        curso = "Física 3",
        tema = "Dielectricos",
        fecha = "12/08/2023",
        catedratico = "Camilo",
        usuario = "DiegoFBI",
        archivos = fakeFiles[1],
        isFav = false
    ),
    Post(
        curso = "Mate discreta",
        tema = "MCD",
        fecha = "02/09/2023",
        catedratico = "Mario",
        usuario = "Walbert3124",
        archivos = fakeFiles[2],
        isFav = false
    ),
    Post(
        curso = "Ecuaciones diferenciales",
        tema = "Variación de parametros",
        fecha = "16/07/2023",
        catedratico = "Curtiss",
        usuario = "NPC1",
        archivos = fakeFiles[3],
        isFav = false
    ),
    Post(
        curso = "Algebra lineal",
        tema = "Vectores",
        fecha = "03/06/2022",
        catedratico = "Mario",
        usuario = "NPC2",
        archivos = fakeFiles[4],
        isFav = false
    )
)