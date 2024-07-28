package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource.dessertList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {
    private var _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private var currentDessertIndex: Int = 0

    /**
     * Increase revenue depending on current dessert price and increment desserts sold.
     * Calls 'updateDessertIndex()' after.
     */
    fun onDessertTap() {
        _uiState.update { currentState ->
            val dessertPrice = dessertList[currentDessertIndex].price
            currentState.copy(
                numberDessertsSold = currentState.numberDessertsSold.inc(),
                totalRevenue = currentState.totalRevenue + dessertPrice
            )
        }

        updateDessertIndex()
    }

    /**
     * Check if the number of desserts sold calls for updating the dessert.
     * If so, update `currentDessertIndex` and the UI state's 'dessertRes' drawable resource id
     */
    private fun updateDessertIndex() {
        val dessertsSold = _uiState.value.numberDessertsSold

        dessertList.forEachIndexed { index, dessert ->
            if (dessertsSold >= dessert.startProductionAmount) {
                currentDessertIndex = index
            }
        }

        _uiState.update { currentState ->
            currentState.copy(
                dessertRes = dessertList[currentDessertIndex].imageId
            )
        }
    }
}
