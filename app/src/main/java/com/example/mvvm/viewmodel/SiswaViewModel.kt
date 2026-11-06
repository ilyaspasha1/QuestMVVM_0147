package com.example.mvvm.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.example.mvvm.model.Siswa
import com.example.mvvm.model.SiswaFormErrorState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SiswaViewModel : ViewModel() {

    private val _statusUI = MutableStateFlow(Siswa())
    val statusUI: StateFlow<Siswa> = _statusUI.asStateFlow()

    private val _errorState = MutableStateFlow(SiswaFormErrorState())
    val errorState: StateFlow<SiswaFormErrorState> = _errorState.asStateFlow()

    fun setSiswa(ls: List<String>) {
        if (ls.isEmpty()) return

        val namaVal = ls.getOrElse(0) { "" }
        val genderVal = ls.getOrElse(1) { "" }
        val alamatVal = ls.getOrElse(2) { "" }
        val nimVal = ls.getOrElse(3) { "" }
        val emailVal = ls.getOrElse(4) { "" }

        _statusUI.update {
            Siswa(
                nama = namaVal,
                gender = genderVal,
                alamat = alamatVal,
                nim = nimVal,
                email = emailVal
            )
        }
    }

    fun saveSiswa(siswa: Siswa) {
        _statusUI.value = siswa
    }

    fun resetState() {
        _statusUI.value = Siswa()
        _errorState.value = SiswaFormErrorState()
    }

    fun validateForm(nama: String, nim: String, email: String, gender: String, alamat: String): Boolean {
        val namaErr = if (nama.isBlank()) "Nama tidak boleh kosong" else if (nama.length < 3) "Nama minimal 3 karakter" else null
        val nimErr = if (nim.isNotBlank() && !nim.all { it.isDigit() }) "NIM harus berupa angka" else null
        val emailErr = if (email.isNotBlank() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) "Format email tidak valid" else null
        val genderErr = if (gender.isBlank()) "Pilih jenis kelamin" else null
        val alamatErr = if (alamat.isBlank()) "Alamat tidak boleh kosong" else null

        _errorState.value = SiswaFormErrorState(
            namaError = namaErr,
            nimError = nimErr,
            emailError = emailErr,
            genderError = genderErr,
            alamatError = alamatErr
        )

        return !_errorState.value.hasErrors && nama.isNotBlank() && gender.isNotBlank() && alamat.isNotBlank()
    }
}
