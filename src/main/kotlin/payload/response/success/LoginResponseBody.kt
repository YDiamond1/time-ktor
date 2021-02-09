package payload.response.success

import payload.response.ResponseBody

data class LoginResponseBody(val token: String) : ResponseBody
