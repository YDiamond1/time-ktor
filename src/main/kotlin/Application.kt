
import com.viartemev.ktor.flyway.FlywayFeature
import dawin.york.util.JsonMapper.defaultMapper
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.features.*
import org.slf4j.event.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.auth.*
import io.ktor.jackson.*
import io.ktor.util.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import routes.schedule
import services.db.DatabaseFactory.initDB
import services.schedule.ScheduleService

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
@ExperimentalCoroutinesApi
@KtorExperimentalAPI
fun Application.module(testing: Boolean = false) {
    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }
    install(DefaultHeaders)

    val db = initDB()
    install(FlywayFeature){
        dataSource = db
    }
//    install(CORS) {
//        method(HttpMethod.Options)
//        method(HttpMethod.Put)
//        method(HttpMethod.Delete)
//        method(HttpMethod.Patch)
//        header(HttpHeaders.Authorization)
//        header("MyCustomHeader")
//        allowCredentials = true
//        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
//    }
//
//    install(Authentication) {
//    }

    install(ContentNegotiation) {
        register(ContentType.Application.Json, JacksonConverter(defaultMapper))
    }
    val scheduleService = ScheduleService()
    install(Routing){
        schedule(schedulerService = scheduleService)
    }
}

