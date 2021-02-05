package dawin.york.util

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

object JsonMapper {

    val defaultMapper = jacksonObjectMapper()

    init{
        defaultMapper.configure(SerializationFeature.INDENT_OUTPUT, true)
        defaultMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
}
