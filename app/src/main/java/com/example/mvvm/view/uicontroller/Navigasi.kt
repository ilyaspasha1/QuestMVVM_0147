package com.example.mvvm.view.uicontroller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mvvm.model.DataJK
import com.example.mvvm.view.FormSiswa
import com.example.mvvm.view.TampilSiswa
import com.example.mvvm.viewmodel.SiswaViewModel
import androidx.compose.ui.res.stringResource

@Composable
fun Navigasi(
    siswaViewModel: SiswaViewModel = viewModel()
) {
    val navController = rememberNavController()
    val statusUI by siswaViewModel.statusUI.collectAsState()

    NavHost(
        navController = navController,
        startDestination = "form_siswa"
    ) {
        composable("form_siswa") {
            FormSiswa(
                pilihanJK = DataJK.JenisK.map {
                    stringResource(id = it)
                },
                onSubmitButtonClicked = { dataSiswa ->
                    siswaViewModel.setSiswa(dataSiswa)
                    navController.navigate("tampil_siswa")
                },
                onResetClicked = {
                    siswaViewModel.resetState()
                }
            )
        }

        composable("tampil_siswa") {
            TampilSiswa(
                statusUiSiswa = statusUI,
                onBackButtonClicked = {
                    navController.popBackStack()
                }
            )
        }
    }
}
