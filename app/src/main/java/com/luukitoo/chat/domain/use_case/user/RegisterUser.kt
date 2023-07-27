package com.luukitoo.chat.domain.use_case.user

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.luukitoo.chat.core.base.UseCase
import com.luukitoo.chat.domain.model.User
import com.luukitoo.chat.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

class RegisterUser @Inject constructor(
    private val userRepository: UserRepository,
    private val auth: FirebaseAuth
) : UseCase<RegistrationModel, Task<AuthResult>> {

    override suspend fun execute(parameter: RegistrationModel): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(
            parameter.email,
            parameter.password
        ).addOnSuccessListener { authResult ->
            CoroutineScope(Dispatchers.IO).launch {
                userRepository.saveUser(
                    user = User(
                        id = authResult.user?.uid,
                        username = parameter.username
                    )
                )
            }
        }
    }
}

data class RegistrationModel(
    val username: String,
    val email: String,
    val password: String
)