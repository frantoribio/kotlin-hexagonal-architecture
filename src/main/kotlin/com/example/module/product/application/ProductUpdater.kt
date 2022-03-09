package com.example.module.product.application

import com.example.module.product.domain.*

class ProductUpdater(
    private val repository: ProductRepository
) {

    // <editor-fold defaultstate="collapsed" desc="Bad 🚫">
//    private val productSearcher = ProductSearcher(repository)
//
//    suspend fun update(id: ProductId, name: ProductName, price: ProductPrice): Result<Product> {
//        val result = productSearcher.search(id)
//        return result.fold(
//            onSuccess = { oldProduct ->
//                val productUpdate = oldProduct
//                    .updateName(name = name)
//                    .updatePrice(price = price)
//
//                repository.update(productUpdate)
//                Result.success(productUpdate)
//            },
//            onFailure = { exception ->
//                Result.failure(exception)
//            }
//        )
//    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Ok ✅">
    private val productSearcher = ProductSearchService(repository)

    suspend fun update(id: ProductId, name: ProductName, price: ProductPrice): Result<Product> {
        val result = productSearcher.search(id)
        return result.fold(
            onSuccess = { oldProduct ->
                val productUpdate = oldProduct
                    .updateName(name)
                    .updatePrice(price)

                repository.update(productUpdate)

                Result.success(productUpdate)
            },
            onFailure = { exception ->
                Result.failure(exception)
            }
        )
    }
    // </editor-fold>
}
