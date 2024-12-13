package com.devmobile.android.journey.usecase

sealed class OperationStateResult {
    data class Error(val exception: Exception) : OperationStateResult()
    data object Success : OperationStateResult()
}