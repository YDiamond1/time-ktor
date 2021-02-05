package services.token

import domain.User

interface TokenService {
    fun generateToken(user: User):String
}
