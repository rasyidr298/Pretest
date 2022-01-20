package id.rrdev.pretest.utils

import id.rrdev.pretest.data.response.DataProduct

interface OnItemClicked {
    fun onIncreaseTransaction(total: Int, position: Int){}
    fun onDecreaseTransaction(total: Int, position: Int){}
    fun onDeleteProductClick(data: DataProduct){}
    fun onUpdateProductClick(data: DataProduct){}
}