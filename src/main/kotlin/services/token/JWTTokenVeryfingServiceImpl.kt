package services.token

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.JWTVerifier

open class JWTTokenVeryfingServiceImpl : JWTTokenVeryfingService {
    override fun builderVerifier(algorithm: Algorithm, issuer: String): JWTVerifier {
        return JWT.require(algorithm)
            .withIssuer(issuer)
            .build()
    }
}
