package com.example.mvvm

import com.example.mvvm.model.Siswa
import com.example.mvvm.viewmodel.SiswaViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SiswaViewModelTest {

    private lateinit var viewModel: SiswaViewModel

    @Before
    fun setUp() {
        viewModel = SiswaViewModel()
    }

    @Test
    fun initialState_isEmptySiswa() {
        val currentSiswa = viewModel.statusUI.value
        assertEquals("", currentSiswa.nama)
        assertEquals("", currentSiswa.gender)
        assertEquals("", currentSiswa.alamat)
        assertEquals("", currentSiswa.nim)
        assertEquals("", currentSiswa.email)
    }

    @Test
    fun setSiswa_updatesStateCorrectly() {
        val inputList = listOf("Budi Santoso", "Laki-laki", "Jl. Merdeka No. 10", "12345678", "budi@example.com")
        viewModel.setSiswa(inputList)

        val currentSiswa = viewModel.statusUI.value
        assertEquals("Budi Santoso", currentSiswa.nama)
        assertEquals("Laki-laki", currentSiswa.gender)
        assertEquals("Jl. Merdeka No. 10", currentSiswa.alamat)
        assertEquals("12345678", currentSiswa.nim)
        assertEquals("budi@example.com", currentSiswa.email)
    }

    @Test
    fun updateSiswaData_updatesStateCorrectly() {
        viewModel.updateSiswaData(
            nama = "Siti Aminah",
            nim = "87654321",
            email = "siti@example.com",
            gender = "Perempuan",
            alamat = "Jl. Sudirman No. 5"
        )

        val currentSiswa = viewModel.statusUI.value
        assertEquals("Siti Aminah", currentSiswa.nama)
        assertEquals("87654321", currentSiswa.nim)
        assertEquals("siti@example.com", currentSiswa.email)
        assertEquals("Perempuan", currentSiswa.gender)
        assertEquals("Jl. Sudirman No. 5", currentSiswa.alamat)
    }

    @Test
    fun resetState_clearsState() {
        viewModel.setSiswa(listOf("Budi", "Laki-laki", "Alamat"))
        viewModel.resetState()

        val currentSiswa = viewModel.statusUI.value
        assertEquals("", currentSiswa.nama)
        assertEquals("", currentSiswa.gender)
        assertEquals("", currentSiswa.alamat)
    }

    @Test
    fun validateForm_validatesCorrectly() {
        val isValid = viewModel.validateForm(
            nama = "Budi Santoso",
            nim = "12345",
            email = "test@domain.com",
            gender = "Laki-laki",
            alamat = "Jl. Merdeka"
        )
        assertTrue(isValid)

        val isInvalid = viewModel.validateForm(
            nama = "",
            nim = "abc",
            email = "invalid-email",
            gender = "",
            alamat = ""
        )
        assertFalse(isInvalid)
    }
}
