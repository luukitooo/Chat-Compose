package com.luukitoo.chat.domain.use_case.user

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.luukitoo.chat.core.base.UseCase
import javax.inject.Inject

class LoginUser @Inject constructor(
    private val auth: FirebaseAuth
) : UseCase<LoginModel, Task<AuthResult>> {

    override suspend fun execute(parameter: LoginModel): Task<AuthResult> {
        return auth.signInWithEmailAndPassword(parameter.email, parameter.password)
    }
}

data class LoginModel(
    val email: String,
    val password: String
)