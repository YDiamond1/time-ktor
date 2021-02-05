package services.token

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import domain.User
import java.util.*

inline class Milliseconds(val value: Long)

class JWTTokenService
    (
    private val algorithm: Algorithm,
    private val expirationPeriod: Milliseconds,
    private val issuer: String
            )
    :TokenService,JWTTokenVeryfingServiceImpl()
{
    override fun generateToken(user:User) : String =
        JWT.create()
            .withSubject("Authentication")
            .withIssuer(issuer)
            .withClaim("username", user.username)
            .withClaim("password", user.password)
            .withExpiresAt(obtainsExpiresDate())
            .sign(algorithm)

    private fun obtainsExpiresDate() = Date(System.currentTimeMillis() + expirationPeriod.value)
}
