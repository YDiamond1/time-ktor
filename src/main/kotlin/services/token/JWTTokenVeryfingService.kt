package services.token

import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.JWTVerifier

interface JWTTokenVeryfingService {
    fun builderVerifier(algorithm: Algorithm, issuer: String) : JWTVerifier
}
