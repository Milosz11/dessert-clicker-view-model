package com.example.dessertclicker.ui

import androidx.annotation.DrawableRes

data class GameUiState(
    val numberDessertsSold: Int = 0,
    val totalRevenue: Int = 0,
    @DrawableRes val dessertRes: Int = 0
)
