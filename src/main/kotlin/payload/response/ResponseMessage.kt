package payload.response

import io.ktor.http.*

data class ResponseMessage(val code: HttpStatusCode,val message: ResponseBody){
    companion object
}

fun ResponseMessage.Companion.Unauthorized(body: ResponseBody) =
    ResponseMessage(HttpStatusCode.Unauthorized, body)
fun ResponseMessage.Companion.OK(body: ResponseBody) =
    ResponseMessage(HttpStatusCode.OK, body)
fun ResponseMessage.Companion.BadRequest(body: ResponseBody)=
    ResponseMessage(HttpStatusCode.BadRequest, body)
