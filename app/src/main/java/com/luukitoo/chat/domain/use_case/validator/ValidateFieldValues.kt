package com.luukitoo.chat.domain.use_case.validator

import com.luukitoo.chat.core.base.UseCase
import javax.inject.Inject

class ValidateFieldValues @Inject constructor() : UseCase<List<String>, Boolean> {

    override suspend fun execute(parameter: List<String>): Boolean {
        return parameter.all { it.isNotBlank() }
    }
}