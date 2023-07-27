package com.luukitoo.chat.core.helper

data class DisposableValue<T: Any?>(private var value: T?) {

    fun get(): T? {
        val rememberedValue = value
        value = null
        return rememberedValue
    }
}
