package services.user

import domain.User

interface UserService {
    suspend fun areCredentialsValid(login: String, pass: String): Boolean?
    suspend fun userDoesExist(login:String): User?
    suspend fun login(user: User): String
    suspend fun createUser(user: User)
}
