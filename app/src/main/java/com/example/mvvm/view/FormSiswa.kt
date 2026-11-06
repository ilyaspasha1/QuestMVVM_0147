package com.example.mvvm.view

import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.mvvm.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormSiswa(
    pilihanJK: List<String>,
    onSubmitButtonClicked: (MutableList<String>) -> Unit,
    modifier: Modifier = Modifier,
    onResetClicked: (() -> Unit)? = null
) {
    var txtNama by remember { mutableStateOf("") }
    var txtNim by remember { mutableStateOf("") }
    var txtEmail by remember { mutableStateOf("") }
    var txtGender by remember { mutableStateOf("") }
    var txtAlamat by remember { mutableStateOf("") }

    val isNamaValid = txtNama.isBlank() || txtNama.length >= 3
    val isNimValid = txtNim.isBlank() || txtNim.all { it.isDigit() }
    val isEmailValid = txtEmail.isBlank() || Patterns.EMAIL_ADDRESS.matcher(txtEmail).matches()

    val isFormValid = txtNama.isNotBlank() && txtNama.length >= 3 &&
            txtGender.isNotBlank() &&
            txtAlamat.isNotBlank() &&
            isNimValid && isEmailValid

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.form_title)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = stringResource(id = R.string.form_subtitle),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Nama Text Field
            OutlinedTextField(
                value = txtNama,
                onValueChange = { txtNama = it },
                label = { Text(stringResource(id = R.string.label_nama)) },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                trailingIcon = {
                    if (txtNama.isNotEmpty()) {
                        IconButton(
                            onClick = { txtNama = "" },
                            modifier = Modifier.semantics { contentDescription = "Clear Nama Button" }
                        ) {
                            Icon(Icons.Default.Clear, contentDescription = null)
                        }
                    }
                },
                isError = !isNamaValid,
                supportingText = {
                    if (!isNamaValid) {
                        Text(stringResource(id = R.string.err_nama_short), color = MaterialTheme.colorScheme.error)
                    }
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { contentDescription = "Input Nama Lengkap" }
            )

            Spacer(modifier = Modifier.height(12.dp))

            // NIM Text Field
            OutlinedTextField(
                value = txtNim,
                onValueChange = { txtNim = it },
                label = { Text(stringResource(id = R.string.label_nim)) },
                leadingIcon = { Icon(Icons.Default.Numbers, contentDescription = null) },
                trailingIcon = {
                    if (txtNim.isNotEmpty()) {
                        IconButton(
                            onClick = { txtNim = "" },
                            modifier = Modifier.semantics { contentDescription = "Clear NIM Button" }
                        ) {
                            Icon(Icons.Default.Clear, contentDescription = null)
                        }
                    }
                },
                isError = !isNimValid,
                supportingText = {
                    if (!isNimValid) {
                        Text(stringResource(id = R.string.err_nim_invalid), color = MaterialTheme.colorScheme.error)
                    }
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { contentDescription = "Input Nomor Induk Mahasiswa atau Siswa" }
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Email Text Field
            OutlinedTextField(
                value = txtEmail,
                onValueChange = { txtEmail = it },
                label = { Text(stringResource(id = R.string.label_email)) },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                trailingIcon = {
                    if (txtEmail.isNotEmpty()) {
                        IconButton(
                            onClick = { txtEmail = "" },
                            modifier = Modifier.semantics { contentDescription = "Clear Email Button" }
                        ) {
                            Icon(Icons.Default.Clear, contentDescription = null)
                        }
                    }
                },
                isError = !isEmailValid,
                supportingText = {
                    if (!isEmailValid) {
                        Text(stringResource(id = R.string.err_email_invalid), color = MaterialTheme.colorScheme.error)
                    }
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { contentDescription = "Input Alamat Email" }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Gender Radio Group
            Text(
                text = stringResource(id = R.string.label_gender),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                pilihanJK.forEach { item ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .semantics { contentDescription = "Pilihan Jenis Kelamin $item" }
                    ) {
                        RadioButton(
                            selected = txtGender == item,
                            onClick = { txtGender = item }
                        )
                        Text(
                            text = item,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Alamat Text Field
            OutlinedTextField(
                value = txtAlamat,
                onValueChange = { txtAlamat = it },
                label = { Text(stringResource(id = R.string.label_alamat)) },
                leadingIcon = { Icon(Icons.Default.Home, contentDescription = null) },
                trailingIcon = {
                    if (txtAlamat.isNotEmpty()) {
                        IconButton(
                            onClick = { txtAlamat = "" },
                            modifier = Modifier.semantics { contentDescription = "Clear Alamat Button" }
                        ) {
                            Icon(Icons.Default.Clear, contentDescription = null)
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .semantics { contentDescription = "Input Alamat Lengkap" },
                maxLines = 4,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (onResetClicked != null) {
                    OutlinedButton(
                        onClick = {
                            txtNama = ""
                            txtNim = ""
                            txtEmail = ""
                            txtGender = ""
                            txtAlamat = ""
                            onResetClicked()
                        },
                        modifier = Modifier
                            .weight(1f)
                            .semantics { contentDescription = "Reset Form Button" }
                    ) {
                        Text(stringResource(id = R.string.btn_reset))
                    }
                }

                Button(
                    onClick = {
                        val dataSiswa = mutableListOf(
                            txtNama,
                            txtGender,
                            txtAlamat,
                            txtNim,
                            txtEmail
                        )
                        onSubmitButtonClicked(dataSiswa)
                    },
                    enabled = isFormValid,
                    modifier = Modifier
                        .weight(1f)
                        .semantics { contentDescription = "Submit Form Button" }
                ) {
                    Text(stringResource(id = R.string.btn_submit))
                }
            }
        }
    }
}
