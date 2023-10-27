package com.example.praktikum4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.praktikum4.Data.DataForm
import com.example.praktikum4.Data.DataSource.jenis
import com.example.praktikum4.Data.DataSource.nikah
import com.example.praktikum4.ui.theme.CobaViewModel
import com.example.praktikum4.ui.theme.Praktikum4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Praktikum4Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Home(message = "Create Your Account", from = "asdas" )
                }
            }
        }
    }
}

@Composable
fun Home(message: String, from :String, modifier: Modifier = Modifier){
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Column (
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(20.dp)
        ){
            Text(text = "Register")
            Text(
                color = Color.Black,
                text = message,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 20.dp, top = 20.dp)
            )
            TampilForm()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TampilForm(
    cobaViewModel: CobaViewModel = viewModel()) {
    var textNama by remember { mutableStateOf("") }
    var texTelp by remember { mutableStateOf("") }
    var textEmail by remember { mutableStateOf("") }
    var textAlamat by remember { mutableStateOf("")
    }

    val context = LocalContext.current
    val dataForm: DataForm
    val uiState by cobaViewModel.uiState.collectAsState()
    dataForm = uiState

    OutlinedTextField(
        value = textNama ,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Nama Lengkap") },
        onValueChange = {
            textNama = it
        }
    )
    OutlinedTextField(
        value = texTelp,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Telpon")},
        onValueChange = {
            texTelp = it
        }
    )
    OutlinedTextField(
        value = textEmail,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Email")},
        onValueChange = {
            textEmail = it
        }
    )
    OutlinedTextField(
        value = textAlamat,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Alamat")},
        onValueChange = {
            textAlamat = it
        }
    )
    SelectJK(
        options = jenis.map { id -> context.resources.getString(id) }
    ) { cobaViewModel.setJenisK(it) }

    SelectNikah(
        options = nikah.map { id -> context.resources.getString(id) }
    ) { cobaViewModel.setNikahK(it) }

    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
          cobaViewModel.insertData(textNama, texTelp, dataForm.sex)
        }
    ) {
        Text(
            text = stringResource(R.string.register),
            fontSize = 16.sp
            )
    }
    Spacer(modifier =  Modifier.height(40.dp))
    Texthasil(
        namanya = cobaViewModel.namaUsr,
        telponnya = cobaViewModel.noTlp,
        jenisnya = cobaViewModel.jenisKl)

}


@Composable
fun SelectJK(
    options: List<String>,
    onSelectionChanged: (String) -> Unit = {}
) {
    var selectedValue by rememberSaveable{ mutableStateOf("") }

    Row(
        modifier = Modifier.padding(5.dp)) {
        Text(text = "Jenis Kelamin : ")
        Spacer(modifier = Modifier.height(10.dp))
        options.forEach { item ->
            Row(
                modifier = Modifier.selectable(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                )
                Text(item)
            }
        }
    }
}
@Composable
fun SelectNikah(
    options: List<String>,
    onSelectionChanged: (String) -> Unit = {}
) {
    var selectedValue by rememberSaveable{ mutableStateOf("") }

    Row(
        modifier = Modifier.padding(5.dp)) {
        Text(text = "Status : ")
        Spacer(modifier = Modifier.height(10.dp))
        options.forEach { item ->
            Row(
                modifier = Modifier.selectable(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                )
                Text(item)
            }
        }
    }
}
@Composable
fun Texthasil(namanya:String,telponnya:String,jenisnya:String) {
    ElevatedCard (
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .size(width = 300.dp, height = 100.dp)
    ){
        Text(text = "Nama : " + namanya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 4.dp))
        Text(text = "Telepon : " + telponnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp))
        Text(text = "Jenis Kelamin : " + jenisnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp))

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Praktikum4Theme {
        Surface {
            Home(message = "Create Your Account", from = "Coba" )
        }
    }
}