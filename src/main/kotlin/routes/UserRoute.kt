package routes

import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.routing.post

fun Route.users(){

    @Location("/login")
    data class PostLogin(val login: String, val password: String )


    route("/user"){
        post<PostLogin>{ user ->

        }
    }
}
