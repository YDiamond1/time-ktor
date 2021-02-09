package services.user

import domain.User
import domain.Users
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import services.db.DatabaseFactory.dbQuery
import services.token.TokenService

class UserServiceImpl (private val tokenService: TokenService) : UserService {
    override suspend fun areCredentialsValid(login: String, pass: String) =
            userDoesExist(login)?.password?.let{
                it == pass
            }




    override suspend fun userDoesExist(login: String) =
        dbQuery {
            Users.select { Users.username eq login }
                .mapNotNull { toUser(it) }
                .singleOrNull()
        }


    override suspend fun login(user: User) = tokenService.generateToken(user)


    override suspend fun createUser(user: User) {
        dbQuery {
            Users.insert {
                it[username] = user.username
                it[password] = user.password
            }
        }
    }
    private fun toUser(row: ResultRow) = User(row[Users.username],row[Users.password])
}
