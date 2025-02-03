package com.example.transactionalapp.src.viewProduct.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.transactionalapp.src.viewProduct.data.model.ViewProductDTO
import com.example.transactionalapp.src.viewProduct.domain.ViewProductUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class ViewProductViewModel(
    private val useCase: ViewProductUseCase
) : ViewModel()  {

    private val _products = MutableStateFlow<List<ViewProductDTO>>(emptyList())
    val products: StateFlow<List<ViewProductDTO>> = _products

    fun loadProducts() {
        viewModelScope.launch {
            _products.value = useCase.execute()
        }
    }
}