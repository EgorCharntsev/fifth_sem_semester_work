package ru.kpfu.itis.impl.usecase

import javax.inject.Inject

class AuthUseCase @Inject constructor() {
    suspend operator fun invoke(email: String, password: String): Boolean {
        return email == "test@example.com" && password == "qwerty123"
    }
}