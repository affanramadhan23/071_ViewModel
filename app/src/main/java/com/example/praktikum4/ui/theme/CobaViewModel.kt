package com.example.praktikum4.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.praktikum4.Data.DataForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CobaViewModel: ViewModel() {
    var namaUsr : String by mutableStateOf("")
        private set
    var noTlp : String by mutableStateOf("")
        private set
    var jenisKl : String by mutableStateOf("")
        private set
    var emailUsr : String by mutableStateOf("")
        private set
    private val _uiState = MutableStateFlow(DataForm())
    val uiState: StateFlow<DataForm> = _uiState.asStateFlow()

    fun BacaData(nm:String,tlp:String, jk:String, eml:String){
        namaUsr=nm;
        noTlp=tlp;
        jenisKl=jk;
        emailUsr=eml;
    }

    fun setJenisK(pilihJK:String) {
        _uiState.update { currentState -> currentState.copy(sex = pilihJK) }
    }
    fun setNikahK(pilihStatus:String) {
        _uiState.update { currentState -> currentState.copy(nikah = pilihStatus) }
    }

    fun insertData(textNama: String, texTelp: String, sex: String) {

    }
}