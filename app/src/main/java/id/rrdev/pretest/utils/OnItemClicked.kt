package id.rrdev.pretest.utils

import id.rrdev.pretest.data.response.DataProduct

interface OnItemClicked {
    fun onEventClick(data: Int){}
    fun onDeleteProductClick(data: DataProduct){}
    fun onUpdateProductClick(data: DataProduct){}
}