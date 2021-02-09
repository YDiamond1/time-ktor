package util.extention

import io.ktor.application.*
import io.ktor.response.*
import payload.response.ResponseMessage

suspend fun ApplicationCall.respondWith(responseMessage: ResponseMessage) =
    respond(responseMessage.code, responseMessage.message)
