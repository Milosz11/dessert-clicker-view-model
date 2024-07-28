package com.example.dessertclicker.ui.test

import androidx.annotation.DrawableRes
import com.example.dessertclicker.R
import com.example.dessertclicker.data.Datasource.dessertList
import com.example.dessertclicker.ui.GameViewModel
import org.junit.Assert.assertEquals
import org.junit.Test

class GameViewModelTest {
    private val viewModel = GameViewModel()

    @Test
    fun gameViewModel_OneTap_ImageUnchangedAndNumberDessertsAndRevenueChanged() {
        val expectedRevenue = dessertList[0].price
        val expectedNumberOfDessertsSold = 1
        @DrawableRes val expectedImageRes = R.drawable.cupcake

        viewModel.onDessertTap()

        val currentGameUiState = viewModel.uiState.value

        // Revenue
        assertEquals(expectedRevenue, currentGameUiState.totalRevenue)
        // Number of desserts sold
        assertEquals(expectedNumberOfDessertsSold, currentGameUiState.numberDessertsSold)
        // Drawable resource id
        assertEquals(expectedImageRes, currentGameUiState.dessertRes)
    }

    @Test
    fun gameViewModel_SixTaps_NumberDessertsAndRevenueAndImageChanged() {
        val expectedRevenue = dessertList[1].startProductionAmount * dessertList[0].price +
                dessertList[1].price
        val expectedNumberOfDessertsSold = 6
        @DrawableRes val expectedImageRes = R.drawable.donut

        repeat(6) {
            viewModel.onDessertTap()
        }

        val currentGameUiState = viewModel.uiState.value

        // Revenue
        assertEquals(expectedRevenue, currentGameUiState.totalRevenue)
        // Number of desserts sold
        assertEquals(expectedNumberOfDessertsSold, currentGameUiState.numberDessertsSold)
        // Drawable resource id
        assertEquals(expectedImageRes, currentGameUiState.dessertRes)
    }
}
