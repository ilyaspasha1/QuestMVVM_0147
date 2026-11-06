package com.example.mvvm.model

data class Siswa(
    val nama: String = "",
    val nim: String = "",
    val email: String = "",
    val gender: String = "",
    val alamat: String = ""
)

data class SiswaFormErrorState(
    val namaError: String? = null,
    val nimError: String? = null,
    val emailError: String? = null,
    val genderError: String? = null,
    val alamatError: String? = null
) {
    val hasErrors: Boolean
        get() = namaError != null || nimError != null || emailError != null || genderError != null || alamatError != null
}
