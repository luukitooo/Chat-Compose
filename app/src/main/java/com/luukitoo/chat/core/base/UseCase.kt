package com.luukitoo.chat.core.base

interface UseCase<Parameter, Result> {
    suspend fun execute(parameter: Parameter): Result
}