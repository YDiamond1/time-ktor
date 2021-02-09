package routes

import domain.User
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.routing.post
import kotlinx.coroutines.ExperimentalCoroutinesApi
import payload.response.BadRequest
import payload.response.OK
import payload.response.ResponseMessage
import payload.response.Unauthorized
import payload.response.error.ErrorResponseBody
import payload.response.success.LoginResponseBody
import services.user.UserService
import util.extention.respondWith
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract




@ExperimentalCoroutinesApi
fun Route.users(userService: UserService){
    @Location("/login")
    data class PostLogin(val login: String?, val password: String?)
    fun PostLogin.toUser() = User(login!!, password!!)

    suspend fun areCredentialsValid(postLogin: PostLogin) =
         userService.areCredentialsValid(postLogin.login!!, postLogin.password!!) ?: false


    fun isInputValid(postLogin: PostLogin): Boolean {
        return postLogin.login != null && postLogin.login != ""
                && postLogin.password != null && postLogin.password != ""
    }

    route("/user"){

        post<PostLogin>{ user ->
           when{
               isInputValid(user).not() ->
                   call.respondWith(ResponseMessage.BadRequest(ErrorResponseBody.InputInvalid))
               areCredentialsValid(user) ->
                   call.respondWith(ResponseMessage.Unauthorized(ErrorResponseBody.AuthorizedFailed))
               else -> {
                   val token = userService.login(user.toUser())
                   call.respondWith(ResponseMessage.OK(LoginResponseBody(token)))
               }

           }
        }


    }
}
