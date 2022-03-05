package com.example.module.product.application

import com.example.module.product.domain.Product
import com.example.module.product.domain.ProductId
import com.example.module.product.domain.ProductRepository

class ProductSearcher(
    private val repository: ProductRepository
) {

    // <editor-fold defaultstate="collapsed" desc="Primera implemetación">
    suspend fun search(id: ProductId): Result<Product> {
        val product = repository.find(id)
        return if (product == null) {
            Result.failure(IllegalArgumentException())
        } else {
            Result.success(product)
        }
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Usando servicios de dominio">
    /*private val productSearchService = ProductSearchService(repository)
    suspend fun search(id: ProductId): Result<Product> {
        return productSearchService.search(id)
    }*/
    // </editor-fold>

}