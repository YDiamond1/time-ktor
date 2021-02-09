package payload.response.error

import payload.response.ResponseBody

abstract class ErrorResponseBody (val errorMessage: String) : ResponseBody {
    object AuthorizedFailed : ErrorResponseBody("AuthorizationFailed")
    object InputInvalid : ErrorResponseBody("InputInvalid")
    object Unknown : ErrorResponseBody("Unknown error")
}
